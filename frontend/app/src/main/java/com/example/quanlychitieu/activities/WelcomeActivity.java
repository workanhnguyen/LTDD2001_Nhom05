package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.SpinnerLanguageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {
    private long backPressedTime;
    List<String> languages = new ArrayList<>();
    Spinner spinnerLanguage;
    Button btnSwitchToSignUp, btnSwitchToSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        languages.add(getString(R.string.language_vn));
        languages.add(getString(R.string.language_en));

        initializeElement();
        handleSwitchToRegisterActivity();
        handleSwitchToLoginActivity();



        // Set the locale based on the user's preferred language


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String selectedLanguage = sharedPreferences.getString("language", "vi");

        setLocale(selectedLanguage);

        if (selectedLanguage.equals("vi")) {
            spinnerLanguage.setSelection(0);
        } else if (selectedLanguage.equals("en")) {
            spinnerLanguage.setSelection(1);
        }

        SpinnerLanguageAdapter adapter = new SpinnerLanguageAdapter(WelcomeActivity.this, languages);
        spinnerLanguage.setAdapter(adapter);

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(WelcomeActivity.this, item, Toast.LENGTH_SHORT).show();
                String selectedLanguage;
                if (position == 0) {
                    selectedLanguage = "vi"; // Vietnamese
                } else {
                    selectedLanguage = "en"; // English
                }

                String currentLanguage = getSelectedLanguage();
                if (!selectedLanguage.equals(currentLanguage)) {
                    setLocale(selectedLanguage);
                    recreate();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void handleSwitchToLoginActivity() {
        btnSwitchToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleSwitchToRegisterActivity() {
        btnSwitchToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeElement() {
        spinnerLanguage = findViewById(R.id.spinnerLanguage);
        btnSwitchToSignUp = findViewById(R.id.btnSwitchToSignUp);
        btnSwitchToSignIn = findViewById(R.id.btnSwitchToSignIn);
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
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(this, getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

//    public void setLocale(Context context, String lang) {
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//        Resources resources = getResources();
//        Configuration config = new Configuration();
//        config.setLocale(locale);
//        resources.updateConfiguration(config, resources.getDisplayMetrics());
//
//        // Save the selected language in SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("language", lang);
//        editor.apply();
//    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = new Configuration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("language", lang);
        editor.apply();
}

    private String getSelectedLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("language", "vi");
    }
}