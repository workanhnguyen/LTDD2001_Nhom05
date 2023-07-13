package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class GeneralSettingHolder extends RecyclerView.ViewHolder {
    TextView generalSettingItemTitle, getGeneralSettingItemValue;
    public GeneralSettingHolder(@NonNull View itemView) {
        super(itemView);

        generalSettingItemTitle = itemView.findViewById(R.id.generalSettingItemTitle);
        getGeneralSettingItemValue = itemView.findViewById(R.id.generalSettingItemValue);
    }

    public TextView getGeneralSettingItemTitle() {
        return generalSettingItemTitle;
    }

    public void setGeneralSettingItemTitle(TextView generalSettingItemTitle) {
        this.generalSettingItemTitle = generalSettingItemTitle;
    }

    public TextView getGetGeneralSettingItemValue() {
        return getGeneralSettingItemValue;
    }

    public void setGetGeneralSettingItemValue(TextView getGeneralSettingItemValue) {
        this.getGeneralSettingItemValue = getGeneralSettingItemValue;
    }
}
