package com.example.quanlychitieu.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.UserSettingAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserSettingActivity extends AppCompatActivity {
    RecyclerView userSettingList;
    List<String> userSettings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }

        userSettingList = findViewById(R.id.userSettingList);

        userSettings.add("Đổi mật khẩu");
        userSettings.add("Đăng xuất");

        UserSettingAdapter adapter = new UserSettingAdapter(userSettings);
        userSettingList.setAdapter(adapter);
        userSettingList.setLayoutManager(new LinearLayoutManager(this));
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSettingActivity.this, android.R.layout.simple_list_item_1, userSettings);
//        userSettingList.setAdapter(adapter);
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