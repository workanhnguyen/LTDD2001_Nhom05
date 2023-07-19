package com.example.quanlychitieu.models;

import java.util.Date;

public class Transaction {
    private Integer id;
    private String description;
    private Date createdDate;
    private Long total;
    private String image;
    private CategoryType categoryTypeId;
    private Wallet walletId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryType getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(CategoryType categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public Wallet getWalletId() {
        return walletId;
    }

    public void setWalletId(Wallet walletId) {
        this.walletId = walletId;
    }
}
