package com.example.quanlychitieu.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.utils.CommonUtil;

import org.parceler.Parcels;

public class WalletDetailActivity extends AppCompatActivity {
    EditText walletDetailBalance, walletDetailName, walletDetailDescription;
    ImageView walletDetailAccountImage;
    TextView walletDetailAccountName;
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
        walletDetailAccountName.setText(String.valueOf(wallet.getAccountType().getName()));
        walletDetailDescription.setText(wallet.getDescription());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.blank_avatar) // Replace with your placeholder image resource
                .error(R.drawable.blank_avatar); // Replace with your error image resource
        Glide.with(WalletDetailActivity.this)
                .load(wallet.getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(walletDetailAccountImage);
    }

    private void initializeWalletData() {
        Parcelable parcelable = getIntent().getParcelableExtra("wallet");
        wallet = Parcels.unwrap(parcelable);
    }

    private void initializeElement() {
        walletDetailBalance = findViewById(R.id.walletDetailBalance);
        walletDetailName = findViewById(R.id.walletDetailName);
        walletDetailDescription = findViewById(R.id.walletDetailDescription);

        walletDetailAccountName = findViewById(R.id.walletDetailAccountName);
        walletDetailAccountImage = findViewById(R.id.walletDetailAccountImage);

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