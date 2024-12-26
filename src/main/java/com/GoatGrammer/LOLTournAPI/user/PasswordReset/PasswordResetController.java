package com.GoatGrammer.LOLTournAPI.user.PasswordReset;

import com.GoatGrammer.LOLTournAPI.user.JwtService;
import com.GoatGrammer.LOLTournAPI.user.UserClass.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@CrossOrigin
public class PasswordResetController {
    @Autowired
    private PasswordResetService passwordResetService;
    @Autowired
    private JwtService jwtService; // Use the interface here

    @Autowired
    private UserService userService;
    @PostMapping("/forgot")
    public ResponseEntity<?> requestPasswordReset(@RequestParam String email) {
        passwordResetService.initiatePasswordReset(email);
        return ResponseEntity.ok("Password reset link has been sent to your email.");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        if (passwordResetService.validatePasswordResetToken(token)) {
            passwordResetService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Password has been reset successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid or expired token.");
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        // Retrieve current cookies from the request
        Cookie[] cookies = request.getCookies();

        // Log existing cookies
        System.out.println("Existing cookies:");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        } else {
            System.out.println("No existing cookies.");
        }

        // Create the cookie to remove
        Cookie cookieToRemove = new Cookie("authToken", null);
        cookieToRemove.setPath("/");
        cookieToRemove.setHttpOnly(true);
        cookieToRemove.setMaxAge(0); // Set the cookie to expire immediately

        // Add the cookie to the response
        response.addCookie(cookieToRemove);

        // Log the cookie being added
        System.out.println("Cookie added: " + cookieToRemove.getName() + "=" + cookieToRemove.getValue());

        // Check cookies in the response (this won't show until after the response is sent)
        // This is just to demonstrate you can add them, but you can't read them back
        System.out.println("Response cookies (not directly accessible):");
        response.getHeaderNames().forEach(headerName ->
                System.out.println(headerName + ": " + response.getHeader(headerName))
        );

        // Return a successful response
        return ResponseEntity.ok().build();
    }


}
