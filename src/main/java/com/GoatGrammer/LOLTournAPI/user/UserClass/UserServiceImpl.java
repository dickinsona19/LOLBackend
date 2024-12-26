package com.GoatGrammer.LOLTournAPI.user.UserClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::convertToDTO)  // Static reference to convert User to UserDTO
                .toList();
    }

    @Override
    public User addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserDTO userDTO = UserDTO.convertToDTO(user.get());  // Convert User to UserDTO
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Add a method to authenticate users by comparing the password
    public boolean checkPassword(User user, String rawPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, user.getPassword());
    }

    @Override
    public void logoutUser(String email) {
        // Retrieve the user by email
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            userRepository.save(user); // Save the updated user back to the database
        }
    }
}
