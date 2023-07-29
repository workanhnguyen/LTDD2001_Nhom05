package com.example.quanlychitieu.dtos.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.DevInfoActivity;
import com.example.quanlychitieu.activities.GeneralSettingActivity;
import com.example.quanlychitieu.holders.SettingHolder;
import com.example.quanlychitieu.models.Setting;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingHolder> {

    private Context context;
    private List<Setting> settings;

    public SettingAdapter(List<Setting> settings) {
        this.settings = settings;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SettingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_setting_item, parent, false);
        return new SettingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingHolder holder, int position) {
        Setting setting = settings.get(position);
        holder.getSettingItemIcon().setImageResource(setting.getIcon());
        holder.getSettingItemText().setText(setting.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.getAdapterPosition() == 0) {
                    Intent intent = new Intent(context, GeneralSettingActivity.class);
                    context.startActivity(intent);
                } else if (holder.getAdapterPosition() == 1) {
                    Intent intent = new Intent(context, DevInfoActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return settings == null ? 0 : settings.size();
    }
}
