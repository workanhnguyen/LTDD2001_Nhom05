package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quanlychitieu.MainActivity;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.configs.LoggingUserInfo;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.presenters.LoginPresenter;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.views.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private long backPressedTime;
    TextView switchToRegister, loginAlert, loginUsername;
    EditText loginPassword;
    Button btnLogin;
    LoginPresenter loginPresenter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        loginPresenter = new LoginPresenter(this);
        sharedPreferences = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);

        initializeElement();
        handleSwitchToRegister();
        handleLogin();
    }

    private void handleLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAlert.setVisibility(View.GONE);

                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();
                String alertInfo = validateLoginInfo(username, password);

                if (alertInfo.trim().isEmpty()) {
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(LoginActivity.this, R.color.light_grey)));
                    btnLogin.setText(getString(R.string.logging));
                    loginPresenter.login(new User(username, password));
                    changeActivityByRole(sharedPreferences.getString("role", ""));
                } else {
                    loginAlert.setVisibility(View.VISIBLE);
                    loginAlert.setText(alertInfo);
                }
            }
        });
    }

    private void changeActivityByRole(String role) {
        if (role.equals(CustomConstant.ROLE_ADMIN)) {
            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
            startActivity(intent);
            finish();
        } else if (role.equals(CustomConstant.ROLE_USER)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private String validateLoginInfo(String username, String password) {
        if (username.trim().isEmpty() || password.trim().isEmpty())
            return getString(R.string.not_empty_field);
        return "";
    }

    private void handleSwitchToRegister() {
        switchToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeElement() {
        loginAlert = findViewById(R.id.loginAlert);
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        switchToRegister = findViewById(R.id.switchToRegister);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void showLoginInfo(User user) {
        btnLogin.setEnabled(true);
        btnLogin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(LoginActivity.this, R.color.primary)));
        btnLogin.setText(getString(R.string.login));
        if (user != null) {
            SharedPreferences sharedPreferences = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id", user.getId());
            editor.putString("firstName", user.getFirstname());
            editor.putString("lastName", user.getLastname());
            editor.putString("username", user.getUsername());
            editor.putString("career", user.getCareer());
            editor.putString("email", user.getEmail());
            editor.putString("imageLink", user.getImageLink());
            editor.putString("role", user.getRole());
            editor.putBoolean("gender", user.isGender());
            editor.apply();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            loginAlert.setVisibility(View.VISIBLE);
            loginAlert.setText(getString(R.string.username_or_password_not_correct));
        }
    }

    @Override
    public void showLoginError() {
        loginAlert.setText(getString(R.string.unknown_error));
    }

//    @Override
//    public void onBackPressed() {
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//            finishAffinity();
//        } else {
//            Toast.makeText(this, getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
//        }
//        backPressedTime = System.currentTimeMillis();
//    }
}