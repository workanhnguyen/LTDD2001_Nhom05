package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.presenters.EditUserInfoPresenter;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.views.EditUserInfoView;

public class EditUserInfoActivity extends AppCompatActivity implements EditUserInfoView {
    ImageView editUserInfoImage;
    TextView editUserInfoAlert;
    EditText editUserInfoFullName, editUserInfoEmail, editUserInfoCareer;
    RadioGroup editUserInfoRgGender;
    RadioButton editUserInfoRbMale, editUserInfoRbFemale;
    Button btnUpdateUser;

    // ---------------------------------------------------
    EditUserInfoPresenter editUserInfoPresenter;
    SharedPreferences userPref;
    UserDto userDto = new UserDto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.edit_info);
            actionBar.setElevation(0);
        }

        editUserInfoPresenter = new EditUserInfoPresenter(this);
        userPref = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);

        initializeElement();
        handleShowDataToUI();
        handleUpdateUser();
    }

    private void handleUpdateUser() {
        btnUpdateUser.setOnClickListener(v -> {
            editUserInfoAlert.setVisibility(View.GONE);

            String fullName = editUserInfoFullName.getText().toString().trim();
            String email = editUserInfoEmail.getText().toString().trim();
            String career = editUserInfoCareer.getText().toString().trim();

            String alertString = validateUserInfoInput(fullName, email);

            if (alertString.trim().isEmpty()) {
                btnUpdateUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditUserInfoActivity.this, R.color.dark_grey)));
                btnUpdateUser.setEnabled(false);
                btnUpdateUser.setText(getString(R.string.updating));

                String[] name = CommonUtil.getFirstAndLastName(fullName);
                String firstName = name[1];
                String lastName = name[0];

                userDto.setFirstname(firstName);
                userDto.setLastname(lastName);
                userDto.setEmail(email);
                userDto.setCareer(career);

                if (editUserInfoRbMale.isChecked()) userDto.setGender(true);
                else userDto.setGender(false);

                editUserInfoPresenter.updateUser(userPref.getInt("id", 1), userDto);
            } else {
                editUserInfoAlert.setVisibility(View.VISIBLE);
                editUserInfoAlert.setText(alertString);
            }
        });
    }

    private String validateUserInfoInput(String fullName, String email) {
        if (fullName.isEmpty() || fullName.isBlank() || email.isEmpty() || email.isBlank())
            return getString(R.string.not_empty_field);
        return "";
    }

    private void handleShowDataToUI() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.blank_avatar)
                .error(R.drawable.blank_avatar);
        Glide.with(EditUserInfoActivity.this)
                .load(userPref.getString("imageLink", ""))
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(editUserInfoImage);

        editUserInfoFullName.setText(userPref.getString("lastName", "") + " " + userPref.getString("firstName", ""));
        editUserInfoEmail.setText(userPref.getString("email", ""));
        editUserInfoCareer.setText(userPref.getString("career", ""));

        if (userPref.getBoolean("gender", true)) {
            editUserInfoRbMale.setChecked(true);
            editUserInfoRbFemale.setChecked(false);
        } else {
            editUserInfoRbMale.setChecked(false);
            editUserInfoRbFemale.setChecked(true);
        }
    }

    private void initializeElement() {
        editUserInfoAlert = findViewById(R.id.editUserInfoAlert);

        editUserInfoImage = findViewById(R.id.editUserInfoImage);

        editUserInfoFullName = findViewById(R.id.editUserInfoFullName);
        editUserInfoEmail = findViewById(R.id.editUserInfoEmail);
        editUserInfoCareer = findViewById(R.id.editUserInfoCareer);

        editUserInfoRgGender = findViewById(R.id.editUserInfoRgGender);
        editUserInfoRbMale = findViewById(R.id.editUserInfoRbMale);
        editUserInfoRbFemale = findViewById(R.id.editUserInfoRbFemale);

        btnUpdateUser = findViewById(R.id.btnUpdateUser);
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
    public void showUpdatedUser(User updatedUser) {

        btnUpdateUser.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditUserInfoActivity.this, R.color.primary)));
        btnUpdateUser.setEnabled(true);
        btnUpdateUser.setText(getString(R.string.update));

        if (updatedUser != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.blank_avatar)
                    .error(R.drawable.blank_avatar);
            Glide.with(EditUserInfoActivity.this)
                    .load(updatedUser.getImageLink())
                    .apply(requestOptions)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(editUserInfoImage);

            editUserInfoFullName.setText(updatedUser.getLastname() + " " + updatedUser.getFirstname());
            editUserInfoEmail.setText(updatedUser.getEmail());
            editUserInfoCareer.setText(updatedUser.getCareer());

            if (updatedUser.isGender()) {
                editUserInfoRbMale.setChecked(true);
                editUserInfoRbFemale.setChecked(false);
            } else {
                editUserInfoRbMale.setChecked(false);
                editUserInfoRbFemale.setChecked(true);
            }

            Toast.makeText(EditUserInfoActivity.this, getString(R.string.updated_successful), Toast.LENGTH_SHORT).show();

            userPref = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userPref.edit();
            editor.putString("firstName", updatedUser.getFirstname());
            editor.putString("lastName", updatedUser.getLastname());
            editor.putString("email", updatedUser.getEmail());
            editor.putString("career", updatedUser.getCareer());
            editor.putBoolean("gender", updatedUser.isGender());
            editor.apply();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(EditUserInfoActivity.this, getString(R.string.updated_failed), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showErrorUpdateUser() {

    }
}