package com.zeus.chatapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImageUtil {
    private static byte[] readImageBytes(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        byte[] data = new byte[(int)imageFile.length()];
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            fis.read(data);
        }

        return data;
    }

    private static String generateHash(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] bytes = md.digest(data);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b)); // bytes -> hex
        }

        return sb.toString();
    }

    public static String getHashCode(String path) {
        try {
            byte[] imageBytes = readImageBytes(path);
            String hash = generateHash(imageBytes, "MD5");
            return hash;
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "Invalid image";
    }
}
