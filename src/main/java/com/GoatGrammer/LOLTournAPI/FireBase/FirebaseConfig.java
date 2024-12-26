package com.GoatGrammer.LOLTournAPI.FireBase;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    public FirebaseConfig() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/hextechbets-firebase-adminsdk-z1p26-c9876487e3.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("hextechbets.firebasestorage.app") // Replace with your bucket name
                .build();

        FirebaseApp.initializeApp(options);
    }
}
