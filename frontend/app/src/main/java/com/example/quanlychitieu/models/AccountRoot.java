package com.example.quanlychitieu.models;

import org.parceler.Parcel;

@Parcel
public class AccountRoot{
    private Integer id;
    private String name;
    private String imageLink;

    public AccountRoot() {
    }

    public AccountRoot(Integer id, String name, String imageLink) {
        this.id = id;
        this.name = name;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
