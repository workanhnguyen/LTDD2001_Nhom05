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
import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.presenters.RegisterPresenter;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.views.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    TextView switchToLogin, registerAlert;
    EditText registerFullName, registerUsername, registerEmail, registerPassword, registerConfirmPassword;
    Button btnRegister;
    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        registerPresenter = new RegisterPresenter(this);

        initializeElement();
        handleSwitchToLogin();
        handleRegister();
    }

    private void handleRegister() {
        btnRegister.setOnClickListener(v -> {

            String fullName = registerFullName.getText().toString().trim();
            String username = registerUsername.getText().toString().trim();
            String email = registerEmail.getText().toString().trim();
            String password = registerPassword.getText().toString().trim();
            String confirmPassword = registerConfirmPassword.getText().toString().trim();

            String alertString = validateRegisterInfo(fullName, username, email, password, confirmPassword);

            if (alertString.trim().isEmpty()) {
                btnRegister.setEnabled(false);
                btnRegister.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(RegisterActivity.this, R.color.light_grey)));
                btnRegister.setText(getString(R.string.registering));

                String[] splitedName = CommonUtil.getFirstAndLastName(fullName);

                UserDto userDto = new UserDto();
                userDto.setFirstname(splitedName[1]);
                userDto.setLastname(splitedName[0]);
                userDto.setUsername(username);
                userDto.setPassword(password);
                userDto.setCareer("");
                userDto.setEmail(email);
                userDto.setGender(true);
                userDto.setImageLink("https://res.cloudinary.com/dougpz2fu/image/upload/v1691309723/mobile-assets/blank_avatar_peyifv.jpg");
                userDto.setRole(CustomConstant.ROLE_USER);

                registerPresenter.register(userDto);
            } else {
                registerAlert.setVisibility(View.VISIBLE);
                registerAlert.setText(alertString);
            }


        });
    }

    private String validateRegisterInfo(String fullName, String username, String email, String password, String confirmPassword) {
        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return getString(R.string.not_empty_field);
        } else if (!password.equals(confirmPassword)) {
            return getString(R.string.password_not_match);
        }

        return "";
    }

    private void handleSwitchToLogin() {
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeElement() {
        switchToLogin = findViewById(R.id.switchToLogin);
        btnRegister = findViewById(R.id.btnRegister);

        registerFullName = findViewById(R.id.registerFullName);
        registerUsername = findViewById(R.id.registerUsername);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPassword = findViewById(R.id.registerConfirmPassword);
        registerAlert = findViewById(R.id.registerAlert);
    }

    @Override
    public void saveRegisterInfo(User user) {
        btnRegister.setEnabled(true);
        btnRegister.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(RegisterActivity.this, R.color.primary)));
        btnRegister.setText(getString(R.string.register));
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
            editor.putString("password", user.getPassword());
            editor.apply();

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            registerAlert.setVisibility(View.VISIBLE);
            registerAlert.setText(getString(R.string.username_existed));
        }
    }

    @Override
    public void showRegisterError() {
        registerAlert.setText(getString(R.string.unknown_error));
    }
}