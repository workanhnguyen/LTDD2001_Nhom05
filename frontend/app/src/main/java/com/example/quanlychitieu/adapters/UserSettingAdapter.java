package com.example.quanlychitieu.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.holders.UserSettingHolder;

import java.util.List;

public class UserSettingAdapter extends RecyclerView.Adapter<UserSettingHolder> {
    private List<String> userSettings;

    public UserSettingAdapter(List<String> userSettings) { this.userSettings = userSettings; }

    @NonNull
    @Override
    public UserSettingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_user_setting_item, parent, false);
        return new UserSettingHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull UserSettingHolder holder, int position) {
        String userSettingItem = userSettings.get(position);
        holder.getUserSettingItem().setText(userSettingItem);

        if (holder.getAdapterPosition() == 1)
            holder.getUserSettingItem().setTextColor(Color.RED);

        holder.itemView.setOnClickListener(v -> Toast.makeText(v.getContext(), userSettingItem, Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return userSettings == null ? 0 : userSettings.size();
    }
}
