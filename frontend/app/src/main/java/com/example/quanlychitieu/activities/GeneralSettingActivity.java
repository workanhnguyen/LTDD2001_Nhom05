package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
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
import com.example.quanlychitieu.adapters.SpinnerLanguageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeneralSettingActivity extends AppCompatActivity {
    Switch switchShowHideBalance;
    Spinner settingSpinnerLanguage;
    SharedPreferences toggleShowBalancePref;

    SharedPreferences languagePreferences;
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

        SpinnerLanguageAdapter adapter = new SpinnerLanguageAdapter(GeneralSettingActivity.this, languages);
        settingSpinnerLanguage.setAdapter(adapter);

        languagePreferences = getSharedPreferences("languagePref", Context.MODE_PRIVATE);
        String selectedLanguage = languagePreferences.getString("language", "vi");
        // Set the locale based on the user's preferred language
        setLocale(selectedLanguage);
        settingSpinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage;
                if (position == 0) {
                    selectedLanguage = "vi"; // Vietnamese
                } else {
                    selectedLanguage = "en"; // English
                }
                String currentLanguage = getSelectedLanguage();
                if (!selectedLanguage.equals(currentLanguage)) {
                    setLocale(selectedLanguage);
                    recreate();
                }
                // Set the selected item in the spinner based on the selected language
                if (selectedLanguage.equals("vi")) {
                    settingSpinnerLanguage.setSelection(0);
                } else if (selectedLanguage.equals("en")) {
                    settingSpinnerLanguage.setSelection(1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
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

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = new Configuration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        languagePreferences = getSharedPreferences("languagePref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = languagePreferences.edit();
        editor.putString("language", lang);
        editor.apply();
    }

    private String getSelectedLanguage() {
        languagePreferences = getSharedPreferences("languagePref", Context.MODE_PRIVATE);
        return languagePreferences.getString("language", "vi");
    }
}