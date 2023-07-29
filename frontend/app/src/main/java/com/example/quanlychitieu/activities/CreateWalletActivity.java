package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.TransactionDto;
import com.example.quanlychitieu.dtos.WalletDto;
import com.example.quanlychitieu.models.AccountType;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.presenters.CreateWalletPresenter;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.utils.DateUtil;
import com.example.quanlychitieu.views.CreateWalletView;

import org.parceler.Parcels;

import java.text.ParseException;
import java.util.Date;

public class CreateWalletActivity extends AppCompatActivity implements CreateWalletView {
    private static final int REQUEST_CODE_SELECT_ACCOUNT = 1;
    LinearLayout linearLayoutCreateWalletChooseAccountRoot;
    Button btnConfrimCreateWallet;
    ImageView createWalletAccountTypeImage;
    TextView createWalletAccountTypeName, createWalletAlert;
    EditText createWalletBalance, createWalletDescription, createWalletName;
    // -------------------------------------------
    AccountType accountType;
    CreateWalletPresenter createWalletPresenter;
    SharedPreferences userPref;

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

        createWalletPresenter = new CreateWalletPresenter(this);
        userPref = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);

        initializeElement();
        handleSwitchToAccountTypeActivity();
        handleCreateNewWallet();
    }

    private void handleCreateNewWallet() {
        btnConfrimCreateWallet.setOnClickListener(v -> {
            createWalletAlert.setVisibility(View.GONE);

            String walletName = createWalletName.getText().toString().trim();
            String walletDescription = createWalletDescription.getText().toString().trim();
            String walletBalance = createWalletBalance.getText().toString().trim();

            String alertString = validateWalletInput(walletName, walletDescription, walletBalance, accountType);

            if (alertString.trim().isEmpty()) {
                btnConfrimCreateWallet.setEnabled(false);
                btnConfrimCreateWallet.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(CreateWalletActivity.this, R.color.dark_grey)));
                btnConfrimCreateWallet.setText(getString(R.string.creating_wallet));

                WalletDto walletDto = new WalletDto();
                walletDto.setName(walletName);
                walletDto.setDescription(walletDescription);
                walletDto.setBalance(Long.parseLong(walletBalance));
                walletDto.setAccountTypeId(accountType.getId());
                walletDto.setUserId(userPref.getInt("id", 1));

                createWalletPresenter.addNewWallet(walletDto);
            } else {
                createWalletAlert.setText(alertString);
                createWalletAlert.setVisibility(View.VISIBLE);
                btnConfrimCreateWallet.setEnabled(true);
                btnConfrimCreateWallet.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(CreateWalletActivity.this, R.color.primary)));
                btnConfrimCreateWallet.setText(getString(R.string.create_wallet));
            }
        });
    }

    private String validateWalletInput(String walletName, String walletDescription, String walletBalance, AccountType accountType) {
        if (walletName.isBlank() || walletDescription.isBlank() || walletBalance.isBlank() || accountType == null)
            return getString(R.string.not_empty_field);
        return "";
    }

    private void handleSwitchToAccountTypeActivity() {
        linearLayoutCreateWalletChooseAccountRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateWalletActivity.this, ChooseAccountTypeActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_ACCOUNT);
            }
        });
    }

    private void initializeElement() {
        linearLayoutCreateWalletChooseAccountRoot = findViewById(R.id.linearLayoutCreateWalletChooseAccountRoot);
        btnConfrimCreateWallet = findViewById(R.id.btnConfrimCreateWallet);

        createWalletAccountTypeImage = findViewById(R.id.createWalletAccountTypeImage);
        createWalletAccountTypeName = findViewById(R.id.createWalletAccountTypeName);
        createWalletAlert = findViewById(R.id.createWalletAlert);

        createWalletBalance = findViewById(R.id.createWalletBalance);
        createWalletName = findViewById(R.id.createWalletName);
        createWalletDescription = findViewById(R.id.createWalletDescription);
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_ACCOUNT && resultCode == RESULT_OK) {

            assert data != null;
            Parcelable parcelable = data.getParcelableExtra("accountType");
            accountType = Parcels.unwrap(parcelable);

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.app_icon_background)
                    .error(R.drawable.app_icon_background);

            Glide.with(CreateWalletActivity.this).load(accountType.getImageLink())
                    .apply(requestOptions).diskCacheStrategy(DiskCacheStrategy.ALL).into(createWalletAccountTypeImage);

            createWalletAccountTypeName.setText(accountType.getName());
        }
    }

    @Override
    public void showAddedWallet(Wallet wallet) {
        if (wallet != null) {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setDescription("Số dư tạo ví mới");
            transactionDto.setCreatedDate(DateUtil.convertDateToSeconds(new Date()));
            transactionDto.setWalletId(wallet.getId());
            transactionDto.setTotal(wallet.getBalance());
            transactionDto.setCategoryTypeId(CustomConstant.CATEGORY_CREATE_WALLET);

            createWalletPresenter.addTransactionByCreateNewWallet(transactionDto);
        }
    }

    @Override
    public void showAddedTransactionByCreateWallet(Transaction transaction) {
        if (transaction != null) {
            createWalletAlert.setVisibility(View.GONE);
            finish();
        }
    }

    @Override
    public void showAddingWalletError() {
        createWalletAlert.setText(getString(R.string.create_wallet_failed));
    }
}