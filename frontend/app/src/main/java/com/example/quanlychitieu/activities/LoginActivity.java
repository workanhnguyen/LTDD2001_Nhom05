package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlychitieu.MainActivity;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.configs.LoggingUserInfo;

public class LoginActivity extends AppCompatActivity {
    private long backPressedTime;
    TextView switchToRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        initializeElement();
        handleSwitchToRegister();
        handleLogin();
    }

    private void handleLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("LoggingUser", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("userId", LoggingUserInfo.user.getId());
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
        switchToRegister = findViewById(R.id.switchToRegister);
        btnLogin = findViewById(R.id.btnLogin);
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