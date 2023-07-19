package com.example.quanlychitieu.models;

import org.parceler.Parcel;

@Parcel
public class CategoryType {
    private Integer id;
    private String name;
    private String imageLink;
    private CategoryRoot categoryRoot;

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

    public CategoryRoot getCategoryRoot() {
        return categoryRoot;
    }

    public void setCategoryRoot(CategoryRoot categoryRoot) {
        this.categoryRoot = categoryRoot;
    }
}
