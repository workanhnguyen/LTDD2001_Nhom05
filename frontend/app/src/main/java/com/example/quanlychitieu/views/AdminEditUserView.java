package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.User;

public interface AdminEditUserView {
    void showUpdatedUser(User user);
    void showDeleteUserResult(Boolean isDeleted);
    void showUpdateError();
    void showDeleteError();
}
