package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.GeneralSettingAdapter;
import com.example.quanlychitieu.models.GeneralSetting;

import java.util.ArrayList;
import java.util.List;

public class GeneralSettingActivity extends AppCompatActivity {
    Switch switchShowHideBalance;
    SharedPreferences toggleShowBalancePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_setting);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.general_setting);
            actionBar.setElevation(0);
        }

        toggleShowBalancePref = getSharedPreferences("setting", Context.MODE_PRIVATE);

        initializeElement();
        handleToggleShowHideBalance();
    }

    private void handleToggleShowHideBalance() {
        switchShowHideBalance.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = toggleShowBalancePref.edit();
            editor.putBoolean("isShowBalance", isChecked);
            editor.apply();

            handleToggleStyle(isChecked);
        });
    }

    private void initializeElement() {
        switchShowHideBalance = findViewById(R.id.btnShowHideBalance);
        switchShowHideBalance.setChecked(toggleShowBalancePref.getBoolean("isShowBalance", true));
        handleToggleStyle(toggleShowBalancePref.getBoolean("isShowBalance", true));
    }

    private void handleToggleStyle(boolean isChecked) {
        if (isChecked) {
            switchShowHideBalance.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(GeneralSettingActivity.this, R.color.primary)));
            switchShowHideBalance.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(GeneralSettingActivity.this, R.color.primary)));
        } else {
            switchShowHideBalance.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(GeneralSettingActivity.this, R.color.dark_grey)));
            switchShowHideBalance.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(GeneralSettingActivity.this, R.color.dark_grey)));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}