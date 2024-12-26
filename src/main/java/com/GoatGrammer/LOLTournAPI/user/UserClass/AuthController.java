package com.GoatGrammer.LOLTournAPI.user.UserClass;


import com.GoatGrammer.LOLTournAPI.user.JwtService;
import com.GoatGrammer.LOLTournAPI.user.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final JwtService jwtService;

    // Inject the JwtService using constructor injection
    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        // Authenticate the user (verify username and password)
        String token = jwtService.generateToken(loginRequest.getUsername());

        // Set token in an HttpOnly cookie
        Cookie cookie = new Cookie("authToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Use true in production (HTTPS)
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days

        response.addCookie(cookie);

        return ResponseEntity.ok("Login successful");
    }
}
