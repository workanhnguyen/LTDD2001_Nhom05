package com.example.quanlychitieu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.models.User;

import org.parceler.Parcels;

public class AdminEditUserActivity extends AppCompatActivity {
    ImageView adminEditUserAvatar;
    EditText adminEditUserUsername, adminEditUserPassword, adminEditUserFullName, adminEditUserEmail, adminEditUserCareer;
    RadioGroup adminEditUserRgGender;
    RadioButton adminEditUserRbMale, adminEditUserRbFemale;
    Button btnDeleteUser, btnSaveUser;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.edit_user);
            actionBar.setElevation(0);
        }

        initializeElement();
        initializeUserData();
        handleShowDataToUI();
    }

    private void handleShowDataToUI() {
        adminEditUserUsername.setText(user.getUsername());
        adminEditUserPassword.setText(user.getPassword());
        adminEditUserFullName.setText(user.getLastname() + " " + user.getFirstname());
        adminEditUserEmail.setText(user.getEmail());
        adminEditUserCareer.setText(user.getCareer());

        if (user.isGender()) {
            adminEditUserRbMale.setChecked(true);
        } else {
            adminEditUserRbFemale.setChecked(true);
        }

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.blank_avatar) // Replace with your placeholder image resource
                .error(R.drawable.blank_avatar); // Replace with your error image resource
        Glide.with(AdminEditUserActivity.this)
                .load(user.getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(adminEditUserAvatar);
    }

    private void initializeUserData() {
        Parcelable parcelable = getIntent().getParcelableExtra("user");
        user = Parcels.unwrap(parcelable);
    }

    private void initializeElement() {
        adminEditUserAvatar = findViewById(R.id.adminEditUserAvatar);

        adminEditUserUsername = findViewById(R.id.adminEditUserUsername);
        adminEditUserPassword = findViewById(R.id.adminEditUserPassword);
        adminEditUserFullName = findViewById(R.id.adminEditUserFullName);
        adminEditUserEmail = findViewById(R.id.adminEditUserEmail);
        adminEditUserCareer = findViewById(R.id.adminEditUserCareer);

        adminEditUserRgGender = findViewById(R.id.adminEditUserRgGender);

        adminEditUserRbMale = findViewById(R.id.adminEditUserRbMale);
        adminEditUserRbFemale = findViewById(R.id.adminEditUserRbFemale);

        btnDeleteUser = findViewById(R.id.btnDeleteUser);
        btnSaveUser = findViewById(R.id.btnSaveUser);
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