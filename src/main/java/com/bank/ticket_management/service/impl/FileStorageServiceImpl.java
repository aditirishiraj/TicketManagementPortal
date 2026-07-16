package com.bank.ticket_management.service.impl;

import com.bank.ticket_management.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String uploadFile(MultipartFile file) {

        // Check if file is empty
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Please select a file.");
        }

        // Maximum file size: 10 MB
        long maxSize = 10 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new RuntimeException("File size must not exceed 10 MB.");
        }

        // Get original filename
        String originalFilename = file.getOriginalFilename();

        // Get file extension
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(
                    originalFilename.lastIndexOf('.') + 1
            ).toLowerCase();
        }

        // Allow only specific file types
        if (!(extension.equals("pdf")
                || extension.equals("png")
                || extension.equals("jpg")
                || extension.equals("jpeg"))) {

            throw new RuntimeException(
                    "Only PDF, PNG, JPG and JPEG files are allowed."
            );
        }

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID() + "_"
                    + StringUtils.cleanPath(originalFilename);

            Path destination = uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Could not upload file.");
        }
    }
}