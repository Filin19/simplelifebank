package com.bank.simplelifebank.dto;

public class AccountDto {

    private Long Id;
    private String accountOwnerName;
    private double balance;

    public AccountDto() {
    }

    public AccountDto(String accountOwnerName, double balance) {
        this.accountOwnerName = accountOwnerName;
        this.balance = balance;
    }

    public AccountDto(Long id, String accountOwnerName, double balance) {
        Id = id;
        this.accountOwnerName = accountOwnerName;
        this.balance = balance;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
