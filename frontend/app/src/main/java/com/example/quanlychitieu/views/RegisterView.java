package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.User;

public interface RegisterView {
    void saveRegisterInfo(User user);
    void showRegisterError();
}
