package com.example.quanlychitieu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.models.Wallet;

public class WalletDetailActivity extends AppCompatActivity {
    EditText walletDetailBalance, walletDetailName, walletDetailDescription;
    TextView walletDetailAccountType;
    Button btnWalletDetailDelete, btnWalletDetailSave;
    Wallet wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.edit_wallet);
            actionBar.setElevation(0);
        }

        initializeElement();
        initializeWalletData();
        handleShowDataToUI();
    }

    private void handleShowDataToUI() {
        walletDetailBalance.setText(String.valueOf(wallet.getBalance()));
        walletDetailName.setText(wallet.getName());
        walletDetailAccountType.setText(String.valueOf(wallet.getAccountTypeId()));
        walletDetailDescription.setText(wallet.getDescription());
    }

    private void initializeWalletData() {
        Intent intent = getIntent();
        wallet = intent.getParcelableExtra("wallet");
    }

    private void initializeElement() {
        walletDetailBalance = findViewById(R.id.walletDetailBalance);
        walletDetailName = findViewById(R.id.walletDetailName);
        walletDetailDescription = findViewById(R.id.walletDetailDescription);

        walletDetailAccountType = findViewById(R.id.walletDetailAccountType);

        btnWalletDetailDelete = findViewById(R.id.btnWalletDetailDelete);
        btnWalletDetailSave = findViewById(R.id.btnWalletDetailSave);
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