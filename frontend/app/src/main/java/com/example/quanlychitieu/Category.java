package com.example.quanlychitieu;

import java.io.Serializable;

public class Category implements Serializable {
    private int img;
    private String categoryName;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
