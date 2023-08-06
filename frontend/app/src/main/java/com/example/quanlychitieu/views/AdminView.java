package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.User;

import java.util.List;

public interface AdminView {
    void showUserList(List<User> list);
    void showError();
}
