package com.example.quanlychitieu.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.quanlychitieu.R;

public class CreateWalletActivity extends AppCompatActivity {
    LinearLayout linearLayoutCreateWalletChooseAccountRoot;
    Button btnConfrimCreateWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.create_wallet);
            actionBar.setElevation(0);
        }

        initializeElement();
        handleSwitchToAccountRootActivity();
        handleCreateNewWallet();
    }

    private void handleCreateNewWallet() {
        btnConfrimCreateWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle create new wallet here

                onBackPressed();
                finish();
            }
        });
    }

    private void handleSwitchToAccountRootActivity() {
        linearLayoutCreateWalletChooseAccountRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateWalletActivity.this, ChooseAccountRootActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeElement() {
        linearLayoutCreateWalletChooseAccountRoot = findViewById(R.id.linearLayoutCreateWalletChooseAccountRoot);
        btnConfrimCreateWallet = findViewById(R.id.btnConfrimCreateWallet);
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