package com.GoatGrammer.LOLTournAPI.user.UserClass;

import com.GoatGrammer.LOLTournAPI.user.JwtService;
import com.GoatGrammer.LOLTournAPI.user.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private JwtService jwtService; // Use the interface here

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }


    // Add a new user
    @PostMapping(value = "/addUser", consumes = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        User savedUser = userService.addUser(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Get a user by ID
    @GetMapping("getUser/{id}")
    public ResponseEntity<UserDTO> getUserWithId(@PathVariable Integer id) {
        return userService.getUserById(id);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Optional<User> userOpt = userService.getUserByEmail(loginRequest.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Check if the password matches
            if (userService.checkPassword(user, loginRequest.getPassword())) {
                // Generate the JWT token
                String token = jwtService.generateToken(user.getEmail());

                // Set the token in an HttpOnly cookie
                Cookie cookie = new Cookie("authToken", token);
                cookie.setHttpOnly(true); // Prevents JavaScript access
                cookie.setSecure(true); // Set to true in production
                cookie.setPath("/"); // Cookie is available throughout the app
                cookie.setMaxAge(7 * 24 * 60 * 60); // Expires in 7 days

                response.addCookie(cookie);

                // Return the user object in the response
                return ResponseEntity.ok(user); // Return 200 OK with user data
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Invalid password
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User not found
        }
    }

    @GetMapping("/checkAuth")
    public ResponseEntity<User> checkAuth(HttpServletRequest request) {
        // Initialize token variable
        String token = null;

        // Retrieve token from cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("authToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // Validate the JWT token
        if (token != null && jwtService.validateToken(token)) {
            System.out.println(token);
            String email = jwtService.extractEmail(token); // Ensure you're calling the correct method to get the email
            Optional<User> userOpt = userService.getUserByEmail(email);
            if (userOpt.isPresent()) {
                return ResponseEntity.ok(userOpt.get()); // Return the authenticated user
            }
        }

        // Return an unauthorized response if token is invalid or user not found
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}