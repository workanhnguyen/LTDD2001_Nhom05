package com.example.quanlychitieu.models;

import org.parceler.Parcel;

@Parcel
public class Wallet {
    private Integer id;
    private String name;
    private long balance;
    private String description;
    private AccountType accountType;
    private User user;
    private String imageLink;

    public Wallet() {
    }

    public Wallet(Integer id, String name, long balance, String description, AccountType accountType, User user, String imageLink) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.description = description;
        this.accountType = accountType;
        this.user = user;
        this.imageLink = imageLink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
