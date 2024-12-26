package com.GoatGrammer.LOLTournAPI.user.PasswordReset;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.GoatGrammer.LOLTournAPI.user.UserClass.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private JavaMailSender mailSender;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Password encoder

    public void initiatePasswordReset(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = UUID.randomUUID().toString();

            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour

            tokenRepository.save(resetToken); // Save the token in the repository

            // Send email with reset link
            String resetLink = "http://yourfrontend.com/reset-password?token=" + token;
            sendEmail(email, resetLink);
        } else {
            // Handle the case when the user is not found
            throw new Error("User with email " + email + " not found.");
        }
    }

    private void sendEmail(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetLink);
        mailSender.send(message);
    }

    public boolean validatePasswordResetToken(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        return resetToken != null && resetToken.getExpiryDate().isAfter(LocalDateTime.now());
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken != null) {
            User user = resetToken.getUser();
            user.setPassword(passwordEncoder.encode(newPassword)); // Hash the password before saving
            userRepository.save(user);
            tokenRepository.delete(resetToken); // Optionally delete the token after use
        } else {
            throw new Error("Invalid or expired password reset token.");
        }
    }
}
