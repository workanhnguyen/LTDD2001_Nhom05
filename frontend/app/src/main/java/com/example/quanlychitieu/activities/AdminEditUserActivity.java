package com.example.quanlychitieu.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.presenters.AdminEditUserPresenter;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.views.AdminEditUserView;

import org.parceler.Parcels;

public class AdminEditUserActivity extends AppCompatActivity implements AdminEditUserView {
    ImageView adminEditUserAvatar;
    TextView adminEditUserAlert;
    EditText adminEditUserUsername, adminEditUserPassword, adminEditUserFullName, adminEditUserEmail, adminEditUserCareer;
    RadioGroup adminEditUserRgGender;
    RadioButton adminEditUserRbMale, adminEditUserRbFemale;
    Button btnDeleteUser, btnSaveUser;

    AdminEditUserPresenter adminEditUserPresenter;
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
        loadUserData();
        handleShowDataToUI();
        handleUpdateUser();
        handleDeleteUser();
    }

    private void handleDeleteUser() {
        btnDeleteUser.setOnClickListener(v -> {
            adminEditUserAlert.setVisibility(View.GONE);

            AlertDialog.Builder builder = new AlertDialog.Builder(AdminEditUserActivity.this);
            builder.setTitle(getString(R.string.delete_user));
            builder.setMessage(getString(R.string.do_you_want_to_delete_this_user));
            builder.setPositiveButton(getString(R.string.ok), (dialog, which) -> {

                btnDeleteUser.setEnabled(false);
                btnDeleteUser.setText(getString(R.string.deleting));
                btnDeleteUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.dark_grey)));

                btnSaveUser.setEnabled(false);
                btnSaveUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.dark_grey)));

                adminEditUserPresenter.deleteUser(user.getId());
            });
            builder.setNegativeButton(getString(R.string.no), (dialog, which) -> {});

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    private void handleUpdateUser() {
        btnSaveUser.setOnClickListener(v -> {

            adminEditUserAlert.setVisibility(View.GONE);

            String password = adminEditUserPassword.getText().toString().trim();
            String fullName = adminEditUserFullName.getText().toString().trim();
            String email = adminEditUserEmail.getText().toString().trim();
            String career = adminEditUserCareer.getText().toString().trim();
            boolean gender = true;
            if (adminEditUserRbMale.isChecked()) gender = true;
            else if (adminEditUserRbFemale.isChecked()) gender = false;

            String alertString = validateUserInput(password, fullName, email);

            if (alertString.trim().isEmpty()) {
                btnDeleteUser.setEnabled(false);
                btnDeleteUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.dark_grey)));

                btnSaveUser.setEnabled(false);
                btnSaveUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.dark_grey)));
                btnSaveUser.setText(getString(R.string.updating));

                String[] name = CommonUtil.getFirstAndLastName(fullName);
                String firstName = name[1];
                String lastName = name[0];

                UserDto userDto = new UserDto();
                userDto.setPassword(password);
                userDto.setFirstname(firstName);
                userDto.setLastname(lastName);
                userDto.setEmail(email);
                userDto.setCareer(career);
                userDto.setGender(gender);

                adminEditUserPresenter.updateUser(user.getId(), userDto);
            } else {
                adminEditUserAlert.setVisibility(View.VISIBLE);
                adminEditUserAlert.setText(alertString);

                btnDeleteUser.setEnabled(true);
                btnDeleteUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.red)));

                btnSaveUser.setEnabled(true);
                btnSaveUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.primary)));
                btnSaveUser.setText(getString(R.string.update));
            }
        });
    }

    private String validateUserInput(String password, String fullName, String email) {
        if (password.isEmpty() || fullName.isEmpty() || email.isEmpty())
            return getString(R.string.not_empty_field);
        return "";
    }

    @SuppressLint("SetTextI18n")
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

    private void loadUserData() {
        Parcelable parcelable = getIntent().getParcelableExtra("user");
        user = Parcels.unwrap(parcelable);
    }

    private void initializeElement() {
        adminEditUserPresenter = new AdminEditUserPresenter(this);

        adminEditUserAvatar = findViewById(R.id.adminEditUserAvatar);
        adminEditUserAlert = findViewById(R.id.adminEditUserAlert);

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

    @Override
    public void showUpdatedUser(User user) {
        if (user != null) {
            adminEditUserAlert.setVisibility(View.GONE);
            Toast.makeText(AdminEditUserActivity.this, getString(R.string.updated_successful), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            adminEditUserAlert.setVisibility(View.VISIBLE);
            adminEditUserAlert.setText(getString(R.string.updated_failed));
        }

        btnDeleteUser.setEnabled(true);
        btnDeleteUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.red)));

        btnSaveUser.setEnabled(true);
        btnSaveUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.primary)));
        btnSaveUser.setText(getString(R.string.update));
    }

    @Override
    public void showDeleteUserResult(Boolean isDeleted) {

        if (isDeleted) {
            adminEditUserAlert.setVisibility(View.GONE);
            finish();
        } else {
            adminEditUserAlert.setVisibility(View.VISIBLE);
            adminEditUserAlert.setText(getString(R.string.delete_user_failed));
        }

        btnDeleteUser.setEnabled(true);
        btnDeleteUser.setText(getString(R.string.delete_user));
        btnDeleteUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.red)));

        btnSaveUser.setEnabled(true);
        btnSaveUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.primary)));
    }

    @Override
    public void showUpdateError() {
        adminEditUserAlert.setVisibility(View.VISIBLE);
        adminEditUserAlert.setText(getString(R.string.updated_failed));

        btnDeleteUser.setEnabled(true);
        btnDeleteUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.red)));

        btnSaveUser.setEnabled(true);
        btnSaveUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.primary)));
        btnSaveUser.setText(getString(R.string.update));
    }

    @Override
    public void showDeleteError() {
        adminEditUserAlert.setVisibility(View.VISIBLE);
        adminEditUserAlert.setText(getString(R.string.delete_user_failed));

        btnDeleteUser.setEnabled(true);
        btnDeleteUser.setText(getString(R.string.delete_user));
        btnDeleteUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.red)));

        btnSaveUser.setEnabled(true);
        btnSaveUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(AdminEditUserActivity.this, R.color.primary)));
    }
}