package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class UserSettingHolder extends RecyclerView.ViewHolder {
    TextView userSettingItem;
    public UserSettingHolder(@NonNull View itemView) {
        super(itemView);

        userSettingItem = itemView.findViewById(R.id.userSettingItemTitle);
    }

    public TextView getUserSettingItem() {
        return userSettingItem;
    }

    public void setUserSettingItem(TextView userSettingItem) {
        this.userSettingItem = userSettingItem;
    }
}
