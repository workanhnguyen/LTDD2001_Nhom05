package com.example.quanlychitieu.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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
    LinearLayout linearLayoutChooseAccountRoot;

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
        loadWalletData();
        handleShowDataToUI();
        handleSwitchToAccountRootActivity();
        handleDeleteButton();
        handleSaveButton();
    }

    private void handleSaveButton() {
        btnWalletDetailSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resolve saving wallet here

                onBackPressed();
                finish();
            }
        });
    }
    private void handleDeleteButton() {
        btnWalletDetailDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WalletDetailActivity.this);
                builder.setTitle(getString(R.string.delete_wallet));
                builder.setMessage(getString(R.string.do_you_want_to_delete_this_wallet));
                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Resolve deleting wallet here

                        onBackPressed();
                        finish();
                    }
                });
                builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý khi người dùng chọn "Không"
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    private void handleSwitchToAccountRootActivity() {
        linearLayoutChooseAccountRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletDetailActivity.this, ChooseAccountRootActivity.class);
                startActivity(intent);
            }
        });
    }
    private void handleShowDataToUI() {
        walletDetailBalance.setText(CommonUtil.getMoneyFormat(wallet.getBalance()).substring(0, CommonUtil.getMoneyFormat(wallet.getBalance()).length() - 1).trim());
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
    private void loadWalletData() {
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

        linearLayoutChooseAccountRoot = findViewById(R.id.linearLayoutChooseAccountRoot);
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