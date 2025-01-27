package com.GoatGrammer.LOLTournAPI.user.UserClass;

import java.util.List;

public class UserDTO {
    private int id;
    private String username;
    private String email;
    private List<LinkedAccountDTO> linkedAccounts;
    private boolean isCurrentlyInA1v1; // Added field

    // Constructor to initialize UserDTO fields
    public UserDTO(int id, String username, String email, List<LinkedAccountDTO> linkedAccounts, boolean isCurrentlyInA1v1) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.linkedAccounts = linkedAccounts;
        this.isCurrentlyInA1v1 = isCurrentlyInA1v1; // Initialize the new field
    }

    // Getters and setters
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

    public List<LinkedAccountDTO> getLinkedAccounts() {
        return linkedAccounts;
    }

    public void setLinkedAccounts(List<LinkedAccountDTO> linkedAccounts) {
        this.linkedAccounts = linkedAccounts;
    }

    public boolean isCurrentlyInA1v1() {
        return isCurrentlyInA1v1;
    }

    public void setCurrentlyInA1v1(boolean currentlyInA1v1) {
        isCurrentlyInA1v1 = currentlyInA1v1;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", linkedAccounts=" + linkedAccounts +
                ", isCurrentlyInA1v1=" + isCurrentlyInA1v1 +
                '}';
    }

    public static UserDTO convertToDTO(User user) {
        List<LinkedAccountDTO> linkedAccounts = user.getLinkedAccounts().stream()
                .map(linkedAccount -> new LinkedAccountDTO(
                        linkedAccount.getAccountIdentifier(),
                        linkedAccount.getAccountType().getAccountTypeName()
                ))
                .toList();

        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), linkedAccounts, user.isCurrentlyInA1v1());
    }
}
