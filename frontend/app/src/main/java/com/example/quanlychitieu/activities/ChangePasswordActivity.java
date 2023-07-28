package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.presenters.ChangePasswordPresenter;
import com.example.quanlychitieu.views.ChangePasswordView;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordView {
    TextView changePasswordAlert;
    EditText changePwCurrentPassword, changePwNewPassword, changePwConfirmPassword;
    Button btnChangePassword;
    SharedPreferences userPref;
    ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.change_password);
            actionBar.setElevation(0);
        }

        changePasswordPresenter = new ChangePasswordPresenter(this);
        userPref = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);

        initializeElement();
        handleChangePassword();
    }

    private void handleChangePassword() {
        btnChangePassword.setOnClickListener(v -> {

            changePasswordAlert.setVisibility(View.GONE);

            String currentPassword = changePwCurrentPassword.getText().toString().trim();
            String newPassword = changePwNewPassword.getText().toString().trim();
            String confirmPassword = changePwConfirmPassword.getText().toString().trim();

            String alertString = validatePasswordInput(currentPassword, newPassword, confirmPassword);

            if (alertString.trim().isEmpty()) {
                UserDto userDto = new UserDto();
                userDto.setPassword(newPassword);

                btnChangePassword.setEnabled(false);
                btnChangePassword.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(ChangePasswordActivity.this, R.color.light_grey)));
                btnChangePassword.setText(getString(R.string.updating));
                changePasswordPresenter.changePassword(userPref.getInt("id", 1), userDto);
            } else {
                changePasswordAlert.setVisibility(View.VISIBLE);
                changePasswordAlert.setText(alertString);
            }
        });
    }

    private String validatePasswordInput(String currentPassword, String newPassword, String confirmPassword) {
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty())
            return getString(R.string.not_empty_field);
        else if (!newPassword.equals(confirmPassword))
            return getString(R.string.password_not_match);
        else if (!userPref.getString("password", "").equals(currentPassword))
            return getString(R.string.current_password_wrong);
        return "";
    }

    private void initializeElement() {
        changePasswordAlert = findViewById(R.id.changePasswordAlert);

        changePwCurrentPassword = findViewById(R.id.changePwCurrentPassword);
        changePwNewPassword = findViewById(R.id.changePwNewPassword);
        changePwConfirmPassword = findViewById(R.id.changePwConfirmPassword);

        btnChangePassword = findViewById(R.id.btnChangePassword);
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
    public void saveUpdatedUser(User user) {
        btnChangePassword.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(ChangePasswordActivity.this, R.color.primary)));
        btnChangePassword.setEnabled(true);
        btnChangePassword.setText(getString(R.string.update));

        if (user != null) {
            SharedPreferences.Editor editor = userPref.edit();
            editor.putString("password", user.getPassword());
            editor.apply();

            Toast.makeText(ChangePasswordActivity.this, getString(R.string.updated_successful), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(ChangePasswordActivity.this, getString(R.string.updated_failed), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showUpdatePasswordError() {
        changePasswordAlert.setText(getString(R.string.change_password_failed));
    }
}