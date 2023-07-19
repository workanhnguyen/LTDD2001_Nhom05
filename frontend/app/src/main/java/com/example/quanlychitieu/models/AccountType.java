package com.example.quanlychitieu.models;

import android.os.Build;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.parceler.Parcel;

@Parcel
public class AccountType {
    private Integer id;
    private String name;
    private String imageLink;
    private String description;
    private AccountRoot accountRoot;

    public AccountType() {
    }

    public AccountType(Integer id) {
        this.id = id;
    }

    public AccountType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AccountType(Integer id, String name, String imageLink, String description, AccountRoot accountRoot) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.description = description;
        this.accountRoot = accountRoot;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountRoot getAccountRoot() {
        return accountRoot;
    }

    public void setAccountRoot(AccountRoot accountRoot) {
        this.accountRoot = accountRoot;
    }
}
