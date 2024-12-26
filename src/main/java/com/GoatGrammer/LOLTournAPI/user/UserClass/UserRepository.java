package com.GoatGrammer.LOLTournAPI.user.UserClass;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    // Optional: Find by email if needed
    Optional<User> findByEmail(String email);
}