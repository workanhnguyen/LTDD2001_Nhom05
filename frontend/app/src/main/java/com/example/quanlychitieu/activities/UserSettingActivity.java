package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.UserSettingAdapter;
import com.example.quanlychitieu.utils.AdapterListener;

import java.util.ArrayList;
import java.util.List;

public class UserSettingActivity extends AppCompatActivity {
    RecyclerView userSettingList;
    ImageView userSettingUserImage;
    TextView userSettingUserFullName, userSettingUserEmail;
    Button btnEditUserInfo;
    List<String> userSettings = new ArrayList<>();
    AdapterListener adapterListener;
    SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);
        adapterListener = this::finishAffinity;

        initializeElement();
        handleShowDataToUI();
        handleSwitchToEditUserInfoActivity();
    }

    private void initializeElement() {
        userSettingList = findViewById(R.id.userSettingList);
        userSettingUserImage = findViewById(R.id.userSettingUserImage);
        userSettingUserFullName = findViewById(R.id.userSettingUserFullName);
        userSettingUserEmail = findViewById(R.id.userSettingUserEmail);

        btnEditUserInfo = findViewById(R.id.btnEditUserInfo);
    }

    private void handleShowDataToUI() {
        userSettings.add(getString(R.string.change_password));
        userSettings.add(getString(R.string.logout));

        UserSettingAdapter adapter = new UserSettingAdapter(userSettings);
        adapter.setContext(UserSettingActivity.this);
        adapter.setAdapterListener(adapterListener);
        userSettingList.setAdapter(adapter);
        userSettingList.setLayoutManager(new LinearLayoutManager(this));

        // Show logging user data
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.blank_avatar)
                .error(R.drawable.blank_avatar);
        Glide.with(UserSettingActivity.this)
                .load(sharedPreferences.getString("imageLink", ""))
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(userSettingUserImage);
        userSettingUserFullName.setText(sharedPreferences.getString("lastName", "") + " " + sharedPreferences.getString("firstName", ""));
        userSettingUserEmail.setText(sharedPreferences.getString("email", ""));
    }

    private void handleSwitchToEditUserInfoActivity() {
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