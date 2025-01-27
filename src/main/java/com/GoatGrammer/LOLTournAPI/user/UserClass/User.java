package com.GoatGrammer.LOLTournAPI.user.UserClass;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 'mappedBy' indicates the owner side
    @JsonManagedReference
    private List<LinkedAccount> linkedAccounts = new ArrayList<>(); // Initialize to avoid NullPointerException

    @Column(name = "is_currently_in_a_1v1", nullable = false)
    private boolean isCurrentlyInA1v1;

    @OneToMany(mappedBy = "user")
    private List<UserEnrollment> userEnrollments;


    public User() {
    }

    // Constructor for creating new users
    public User(String username, String email, String password, List<LinkedAccount> linkedAccounts, boolean isCurrentlyInA1v1) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.linkedAccounts = linkedAccounts;
        this.isCurrentlyInA1v1 = isCurrentlyInA1v1;
    }
    public List<LinkedAccount> getLinkedAccounts() {
        return linkedAccounts;
    }

    public void setLinkedAccounts(List<LinkedAccount> linkedAccounts) {
        this.linkedAccounts = linkedAccounts;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCurrentlyInA1v1() {
        return isCurrentlyInA1v1;
    }

    public void setCurrentlyInA1v1(boolean currentlyInA1v1) {
        isCurrentlyInA1v1 = currentlyInA1v1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
