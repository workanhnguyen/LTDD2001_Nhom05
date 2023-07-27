package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.User;

public interface LoginView {
    void saveLoginInfo(User user);
    void showLoginError();
}
