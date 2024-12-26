package com.GoatGrammer.LOLTournAPI.FireBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String filePath = firebaseStorageService.uploadFile(file);  // Assuming this method saves the file and returns the file path or URL
        return filePath;
    }
}