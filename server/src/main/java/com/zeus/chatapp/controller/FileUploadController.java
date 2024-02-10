package com.zeus.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.zeus.chatapp.exception.StorageFileNotFoundException;
import com.zeus.chatapp.service.StorageService;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload-file")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		storageService.store(file);

		return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
	}

    @ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
