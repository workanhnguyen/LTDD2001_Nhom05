package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class AdminUserHolder extends RecyclerView.ViewHolder {
    ImageView adminUserAvatar;
    TextView adminUserName, adminUserEmail;
    public AdminUserHolder(@NonNull View itemView) {
        super(itemView);

        adminUserAvatar = itemView.findViewById(R.id.adminUserAvatar);
        adminUserName = itemView.findViewById(R.id.adminUserName);
        adminUserEmail = itemView.findViewById(R.id.adminUserEmail);
    }

    public ImageView getAdminUserAvatar() {
        return adminUserAvatar;
    }

    public void setAdminUserAvatar(ImageView adminUserAvatar) {
        this.adminUserAvatar = adminUserAvatar;
    }

    public TextView getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(TextView adminUserName) {
        this.adminUserName = adminUserName;
    }

    public TextView getAdminUserEmail() {
        return adminUserEmail;
    }

    public void setAdminUserEmail(TextView adminUserEmail) {
        this.adminUserEmail = adminUserEmail;
    }
}
