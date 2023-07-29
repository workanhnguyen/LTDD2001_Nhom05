package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;

public class GeneralSettingActivity extends AppCompatActivity {
    Switch switchShowHideBalance;
    Spinner settingSpinnerLanguage;
    SharedPreferences toggleShowBalancePref;
    List<String> languages = new ArrayList<>();

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
        handleChangeLanguage();
    }

    private void handleChangeLanguage() {

        languages.add(getString(R.string.language_vn));
        languages.add(getString(R.string.language_en));
        settingSpinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

        settingSpinnerLanguage = findViewById(R.id.settingSpinnerLanguage);
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

    private String getLanguageName() {
        SharedPreferences languagePreferences = getSharedPreferences("languagePref", Context.MODE_PRIVATE);
        String selectedLanguage = languagePreferences.getString("language", "vi");

        if (selectedLanguage.equals("vi")) {
            return getString(R.string.language_vn);
        } else if (selectedLanguage.equals("en")) {
            return getString(R.string.language_en);
        }
        return getString(R.string.language_vn); //
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