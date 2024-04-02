package com.zeus.chatapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zeus.chatapp.config.StorageProperties;
import com.zeus.chatapp.exception.StorageException;
import com.zeus.chatapp.exception.StorageFileNotFoundException;

// @Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        if (properties.getStorageLocation().trim().length() == 0) {
            throw new StorageException("File upload location cannot be empty!");
        }

        this.rootLocation = Paths.get(properties.getStorageLocation());
    }

    @Override
    public void init() {
        try {
            // Creates a directory by creating all nonexistent parent directories first.
            // Unlike the createDirectory method, an exception is not thrown if the
            // directory could not be created because it already exists.
            Files.createDirectories(rootLocation);
        } catch (IOException ex) {
            throw new StorageException("Could not initialize storage", ex);
        }
    }

    @Override
    // Forcefully copy file into the database whether it exists or not.
    // purpose of this code is to construct a path to the destination file
    // within the root location, using the original filename of the uploaded file.
    // The resulting destinationFile path can then be used to write the uploaded
    // file to the appropriate location on the file system.
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            Path destinationFile = this.rootLocation
                                        // Append uploaded filename to the rootLocation
                                        .resolve(Paths.get(file.getOriginalFilename()))
                                        // Remove all redundant symbols (e.g., `.`, `..`)
                                        .normalize()
                                        // Convert to an absolute path (e.g., C:/web/uploaded)
                                        .toAbsolutePath();

            // Limit the files to be under the rootLocation path.
            // This security check is essential to prevent directory traversal
            // attacks, where an attacker tries to manipulate file paths to
            // access or write files outside the intended directory structure.
            // By enforcing that all stored files must be within the root directory,
            // you can mitigate the risk of such attacks and ensure the integrity
            // and security of your file storage system.
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(
                    inputStream,
                    destinationFile,
                    StandardCopyOption.REPLACE_EXISTING
                );
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    // Used to obtain the relative paths of all immediate children of rootLocation
    public Stream<Path> loadAll() {
        try {
            return
                // Do a DFS rooted at rootLocation upto 1 child directory (depth)
                Files.walk(this.rootLocation, 1)
                    // Filter out the root location itself
                    .filter(path -> !path.equals(this.rootLocation))
                    // Convert absolute path to its relative path
                    .map(this.rootLocation::relativize);
        } catch (IOException ex) {
            throw new StorageException("Failed to read stored files!", ex);
        }
    }

    @Override
    // Load the filename into the context
    public Path load(String filename) {
        // Dynamically create a Path object while appending filename to the rootLocation
        return rootLocation.resolve(filename);
    }

    @Override
    // By using resources, your code can be more portable across different
    // environments. For example, the same code can work with files stored on
    // the local file system during development and testing, and then switch to
    // files stored in a cloud storage service in production without needing to
    // change the code.
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, ex);
        }
    }

    @Override
    public void deleteAll() {
        // Removes all directories and subdirectories of rootLocation
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

}
