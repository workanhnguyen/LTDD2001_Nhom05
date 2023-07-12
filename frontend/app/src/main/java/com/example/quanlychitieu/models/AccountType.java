package com.example.quanlychitieu.models;

public class AccountType {
    private Integer id;
    private String name;
    private String image;
    private String description;
    private Integer accountRootId;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAccountRootId() {
        return accountRootId;
    }

    public void setAccountRootId(Integer accountRootId) {
        this.accountRootId = accountRootId;
    }
}
