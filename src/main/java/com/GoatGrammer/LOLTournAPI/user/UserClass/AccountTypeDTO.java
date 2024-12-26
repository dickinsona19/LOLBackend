package com.GoatGrammer.LOLTournAPI.user.UserClass;

public class AccountTypeDTO {
    private String accountTypeName;

    // Constructor to initialize accountTypeName
    public AccountTypeDTO(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    // Getter for accountTypeName
    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @Override
    public String toString() {
        return "AccountTypeDTO{" +
                "accountTypeName='" + accountTypeName + '\'' +
                '}';
    }
}
