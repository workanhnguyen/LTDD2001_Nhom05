package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class SettingHolder extends RecyclerView.ViewHolder {

    ImageView settingItemIcon;
    TextView settingItemText;

    public SettingHolder(@NonNull View itemView) {
        super(itemView);

        settingItemIcon = itemView.findViewById(R.id.settingItemIcon);
        settingItemText = itemView.findViewById(R.id.settingItemText);
    }

    public ImageView getSettingItemIcon() {
        return settingItemIcon;
    }

    public void setSettingItemIcon(ImageView settingItemIcon) {
        this.settingItemIcon = settingItemIcon;
    }

    public TextView getSettingItemText() {
        return settingItemText;
    }

    public void setSettingItemText(TextView settingItemText) {
        this.settingItemText = settingItemText;
    }
}
