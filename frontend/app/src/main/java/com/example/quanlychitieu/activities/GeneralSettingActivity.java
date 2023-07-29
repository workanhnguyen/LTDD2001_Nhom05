package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.adapters.GeneralSettingAdapter;
import com.example.quanlychitieu.models.GeneralSetting;

import java.util.ArrayList;
import java.util.List;

public class GeneralSettingActivity extends AppCompatActivity {

    RecyclerView generalSettingList;

    List<GeneralSetting> generalSettings = new ArrayList<>();

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

        generalSettingList = findViewById(R.id.generalSettingList);

        generalSettings.add(new GeneralSetting(getString(R.string.language), getLanguageName()));
        generalSettings.add(new GeneralSetting(getString(R.string.format_time), "dd/MM/yyyy"));

        GeneralSettingAdapter adapter = new GeneralSettingAdapter(generalSettings);
        generalSettingList.setAdapter(adapter);
        generalSettingList.setLayoutManager(new LinearLayoutManager(GeneralSettingActivity.this));
    }

    private String getLanguageName() {
        SharedPreferences languagePreferences = getSharedPreferences("languagePref", Context.MODE_PRIVATE);
        String selectedLanguage = languagePreferences.getString("language", "vi");

        if (selectedLanguage.equals("vi")) {
            return getString(R.string.language_vn);
        } else if (selectedLanguage.equals("en")) {
            return getString(R.string.language_en);
        }
        return getString(R.string.language_vn);
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