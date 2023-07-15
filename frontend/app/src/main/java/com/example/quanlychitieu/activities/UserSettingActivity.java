package com.example.quanlychitieu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.UserSettingAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserSettingActivity extends AppCompatActivity {
    RecyclerView userSettingList;
    Button btnEditUserInfo;
    List<String> userSettings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.user);
            actionBar.setElevation(0);
        }

        userSettingList = findViewById(R.id.userSettingList);

        userSettings.add(getString(R.string.change_password));
        userSettings.add(getString(R.string.logout));

        UserSettingAdapter adapter = new UserSettingAdapter(userSettings);
        userSettingList.setAdapter(adapter);
        userSettingList.setLayoutManager(new LinearLayoutManager(this));

        handleChangeIntentEditUserInfo();
    }

    private void handleChangeIntentEditUserInfo() {
        btnEditUserInfo = findViewById(R.id.btnEditUserInfo);

        btnEditUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSettingActivity.this, EditUserInfoActivity.class);
                startActivity(intent);
            }
        });
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