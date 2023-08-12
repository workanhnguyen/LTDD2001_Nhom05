package com.example.quanlychitieu.models;

import org.parceler.Parcel;

@Parcel
public class CategoryType {
    private Integer id;
    private String name;
    private String image;
    private CategoryRoot categoryRoot;

    public CategoryType() {
    }

    public CategoryType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryType(Integer id, String name, String imageLink) {
        this.id = id;
        this.name = name;
        this.image = imageLink;
    }

    public CategoryType(Integer id, String name, String imageLink, CategoryRoot categoryRoot) {
        this.id = id;
        this.name = name;
        this.image = imageLink;
        this.categoryRoot = categoryRoot;
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
        return image;
    }

    public void setImageLink(String imageLink) {
        this.image = imageLink;
    }

    public CategoryRoot getCategoryRoot() {
        return categoryRoot;
    }

    public void setCategoryRoot(CategoryRoot categoryRoot) {
        this.categoryRoot = categoryRoot;
    }
}
