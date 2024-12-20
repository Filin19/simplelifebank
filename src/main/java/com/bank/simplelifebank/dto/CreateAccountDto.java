package com.bank.simplelifebank.dto;

public class CreateAccountDto {

    private String accountOwnerName;
    private double balance;

    public CreateAccountDto(String accountOwnerName, double balance) {
        this.accountOwnerName = accountOwnerName;
        this.balance = balance;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
