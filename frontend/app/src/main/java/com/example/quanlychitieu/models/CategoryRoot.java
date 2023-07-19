package com.example.quanlychitieu.models;

import org.parceler.Parcel;

@Parcel
public class CategoryRoot {
    private Integer id;
    private String name;
    private String imageLink;
    private String type;

    public CategoryRoot() {
    }

    public CategoryRoot(Integer id, String name, String imageLink, String type) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
