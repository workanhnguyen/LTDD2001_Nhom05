package com.example.quanlychitieu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.holders.SettingHolder;
import com.example.quanlychitieu.models.Setting;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingHolder> {
    private List<Setting> settings;

    public SettingAdapter(List<Setting> settings) {
        this.settings = settings;
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
                Toast.makeText(v.getContext(), setting.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return settings == null ? 0 : settings.size();
    }
}
