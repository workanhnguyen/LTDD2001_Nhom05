package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.User;

public interface ChangePasswordView {
    void saveUpdatedUser(User user);
    void showUpdatePasswordError();
}
