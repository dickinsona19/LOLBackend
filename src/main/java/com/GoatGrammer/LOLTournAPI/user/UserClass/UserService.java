package com.GoatGrammer.LOLTournAPI.user.UserClass;


import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getUsers();
    User addUser(User user);
    ResponseEntity<UserDTO> getUserById(Integer id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    boolean checkPassword(User user, String rawPassword);
    void logoutUser(String email);
    User updateCurrentlyInA1v1Status(int userId, boolean isParticipating);

}
