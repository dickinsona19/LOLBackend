package com.GoatGrammer.LOLTournAPI.user.UserClass;

public class LinkedAccountDTO {
    private String accountIdentifier;
    private String accountTypeName;

    // Constructor to initialize LinkedAccountDTO fields
    public LinkedAccountDTO(String accountIdentifier, String accountTypeName) {
        this.accountIdentifier = accountIdentifier;
        this.accountTypeName = accountTypeName;
    }

    // Getter and Setter for accountIdentifier
    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    // Getter and Setter for accountTypeName
    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @Override
    public String toString() {
        return "LinkedAccountDTO{" +
                "accountIdentifier='" + accountIdentifier + '\'' +
                ", accountTypeName='" + accountTypeName + '\'' +
                '}';
    }
}
