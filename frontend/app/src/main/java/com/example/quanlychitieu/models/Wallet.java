package com.example.quanlychitieu.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Wallet implements Parcelable {
    private Integer id;
    private String name;
    private long balance;
    private String description;
    private Integer accountTypeId;
    private Integer userId;

    private String imageLink;

    public Wallet(Integer id, String name, long balance, String description, Integer accountTypeId, Integer userId, String imageLink) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.description = description;
        this.accountTypeId = accountTypeId;
        this.userId = userId;
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

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeLong(this.balance);
        dest.writeString(this.imageLink);
        dest.writeInt(this.accountTypeId);
        dest.writeInt(this.userId);
    }

    public static final Parcelable.Creator<Wallet> CREATOR = new Parcelable.Creator<Wallet>() {
        @Override
        public Wallet createFromParcel(Parcel source) {
            return new Wallet(source);
        }

        @Override
        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };

    private Wallet(Parcel source) {
        id = source.readInt();
        name = source.readString();
        description = source.readString();
        balance = source.readLong();
        accountTypeId = source.readInt();
        userId = source.readInt();
        imageLink = source.readString();
    }
}
