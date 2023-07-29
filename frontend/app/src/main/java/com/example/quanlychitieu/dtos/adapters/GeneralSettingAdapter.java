package com.example.quanlychitieu.dtos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.holders.GeneralSettingHolder;
import com.example.quanlychitieu.models.GeneralSetting;

import java.util.List;

public class GeneralSettingAdapter extends RecyclerView.Adapter<GeneralSettingHolder> {
    List<GeneralSetting> generalSettings;

    public GeneralSettingAdapter(List<GeneralSetting> generalSettings) {
        this.generalSettings = generalSettings;
    }

    @NonNull
    @Override
    public GeneralSettingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_general_setting_item, parent, false);

        return new GeneralSettingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralSettingHolder holder, int position) {
        GeneralSetting generalSetting = generalSettings.get(position);

        holder.getGeneralSettingItemTitle().setText(generalSetting.getTitle());
        holder.getGetGeneralSettingItemValue().setText(generalSetting.getValue());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), generalSetting.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return generalSettings == null ? 0 : generalSettings.size();
    }
}
