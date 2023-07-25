package com.example.quanlychitieu.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.ChangePasswordActivity;
import com.example.quanlychitieu.activities.WelcomeActivity;
import com.example.quanlychitieu.holders.UserSettingHolder;
import com.example.quanlychitieu.utils.AdapterListener;

import java.util.List;

public class UserSettingAdapter extends RecyclerView.Adapter<UserSettingHolder> {
    private Context context;
    private AdapterListener adapterListener;
    private List<String> userSettings;

    public UserSettingAdapter(List<String> userSettings) { this.userSettings = userSettings; }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterListener getAdapterListener() {
        return adapterListener;
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.getAdapterPosition() == 0) {
                    Intent intent = new Intent(context, ChangePasswordActivity.class);
                    context.startActivity(intent);
                } else {
                    if (adapterListener != null) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("loggingUser", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.clear();
                        editor.apply();

                        Intent intent = new Intent(context, WelcomeActivity.class);
                        context.startActivity(intent);
                        adapterListener.onFinishActivity();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userSettings == null ? 0 : userSettings.size();
    }
}
