package com.GoatGrammer.LOLTournAPI.FireBase;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseStorageService {

    public String uploadFile(MultipartFile file) {
        try {
            String bucketName = "hextechbets.firebasestorage.app";  // Replace with your Firebase bucket name
            String fileName = "profile_pics/" + System.currentTimeMillis() + "-" + file.getOriginalFilename();

            // Upload file to Firebase Storage
            Blob blob = StorageClient.getInstance().bucket(bucketName).create(fileName, file.getInputStream(), file.getContentType());

            // Return the file path or public URL of the uploaded file
            return "https://storage.googleapis.com/" + bucketName + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
