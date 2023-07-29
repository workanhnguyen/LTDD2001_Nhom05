package com.example.quanlychitieu.activities;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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
import com.example.quanlychitieu.presenters.EditWalletPresenter;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.utils.DateUtil;
import com.example.quanlychitieu.views.EditWalletView;

import org.parceler.Parcels;

import java.util.Date;

public class EditWalletActivity extends AppCompatActivity implements EditWalletView {
    private static final int REQUEST_CODE_SELECT_ACCOUNT = 1;
    EditText walletDetailBalance, walletDetailName, walletDetailDescription;
    ImageView walletDetailAccountImage;
    TextView walletDetailAccountName, editWalletAlert;
    Button btnWalletDetailDelete, btnWalletDetailSave;
    LinearLayout linearLayoutChooseAccountRoot;

    // -----------------------------
    AccountType accountType;
    Wallet wallet;
    EditWalletPresenter editWalletPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wallet);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.edit_wallet);
            actionBar.setElevation(0);
        }

        editWalletPresenter = new EditWalletPresenter(this);

        initializeElement();
        loadWalletData();
        handleShowDataToUI();
        handleSwitchToAccountTypeActivity();
        handleDeleteButton();
        handleSaveButton();
    }

    private void handleSaveButton() {
        btnWalletDetailSave.setOnClickListener(v -> {
            editWalletAlert.setVisibility(View.GONE);

            String walletBalance = walletDetailBalance.getText().toString().replace(".", "").trim();
            String walletName = walletDetailName.getText().toString().trim();
            String walletDescription = walletDetailDescription.getText().toString().trim();

            String alertString = validateWalletInput(walletBalance, walletName, walletDescription);

            if (alertString.trim().isEmpty()) {
                btnWalletDetailDelete.setEnabled(false);
                btnWalletDetailDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditWalletActivity.this, R.color.dark_grey)));

                btnWalletDetailSave.setEnabled(false);
                btnWalletDetailSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditWalletActivity.this, R.color.dark_grey)));
                btnWalletDetailSave.setText(getString(R.string.saving));

                WalletDto walletDto = new WalletDto();
                walletDto.setBalance(Long.parseLong(walletBalance));
                walletDto.setName(walletName);
                walletDto.setAccountTypeId(wallet.getAccountType().getId());
                walletDto.setDescription(walletDescription);

                editWalletPresenter.updateWallet(wallet.getId(), walletDto);
            } else {
                editWalletAlert.setVisibility(View.VISIBLE);
                editWalletAlert.setText(alertString);

                btnWalletDetailDelete.setEnabled(true);
                btnWalletDetailDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditWalletActivity.this, R.color.red)));

                btnWalletDetailSave.setEnabled(true);
                btnWalletDetailSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditWalletActivity.this, R.color.primary)));
            }
        });
    }

    private String validateWalletInput(String walletBalance, String walletName, String walletDescription) {
        if (walletBalance.isEmpty() || walletName.isEmpty() || walletDescription.isEmpty())
            return getString(R.string.not_empty_field);
        return "";
    }

    private void handleDeleteButton() {
        btnWalletDetailDelete.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(EditWalletActivity.this);
            builder.setTitle(getString(R.string.delete_wallet));
            builder.setMessage(getString(R.string.do_you_want_to_delete_this_wallet));

            builder.setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                btnWalletDetailDelete.setEnabled(false);
                btnWalletDetailDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditWalletActivity.this, R.color.dark_grey)));
                btnWalletDetailDelete.setText(getString(R.string.deleting_wallet));

                btnWalletDetailSave.setEnabled(false);
                btnWalletDetailSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditWalletActivity.this, R.color.dark_grey)));

                editWalletPresenter.deleteWallet(wallet.getId());
            });

            builder.setNegativeButton(getString(R.string.no), (dialog, which) -> {});
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
    private void handleSwitchToAccountTypeActivity() {
        linearLayoutChooseAccountRoot.setOnClickListener(v -> {
            Intent intent = new Intent(EditWalletActivity.this, ChooseAccountTypeActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_ACCOUNT);
        });
    }
    private void handleShowDataToUI() {
        walletDetailBalance.setText(CommonUtil.getMoneyFormat(wallet.getBalance()).substring(0, CommonUtil.getMoneyFormat(wallet.getBalance()).length() - 2));
        walletDetailName.setText(wallet.getName());
        walletDetailAccountName.setText(String.valueOf(wallet.getAccountType().getName()));
        walletDetailDescription.setText(wallet.getDescription());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.app_icon_background) // Replace with your placeholder image resource
                .error(R.drawable.app_icon_background); // Replace with your error image resource
        Glide.with(EditWalletActivity.this)
                .load(wallet.getAccountType().getImageLink())
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
        editWalletAlert = findViewById(R.id.editWalletAlert);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_ACCOUNT && resultCode == RESULT_OK) {

            assert data != null;
            Parcelable parcelable = data.getParcelableExtra("accountType");
            wallet.setAccountType(Parcels.unwrap(parcelable));

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.app_icon_background)
                    .error(R.drawable.app_icon_background);

            Glide.with(EditWalletActivity.this).load(wallet.getAccountType().getImageLink())
                    .apply(requestOptions).diskCacheStrategy(DiskCacheStrategy.ALL).into(walletDetailAccountImage);

            walletDetailAccountName.setText(wallet.getAccountType().getName());
        }
    }

    @Override
    public void showUpdatedWallet(Wallet updatedWallet) {
        if (updatedWallet != null) {
            editWalletAlert.setVisibility(View.GONE);
            long difference = updatedWallet.getBalance() - wallet.getBalance();
            if (difference > 0) {
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setWalletId(updatedWallet.getId());
                transactionDto.setTotal(difference);
                transactionDto.setCategoryTypeId(CustomConstant.CATEGORY_EDIT_WALLET_GREATER);
                transactionDto.setDescription("Chênh lệch chỉnh sửa ví");
                transactionDto.setCreatedDate(DateUtil.convertDateToSeconds(new Date()));

                editWalletPresenter.addNewTransactionByUpdateWallet(transactionDto);
            } else if (difference < 0) {
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setWalletId(updatedWallet.getId());
                transactionDto.setTotal(difference);
                transactionDto.setCategoryTypeId(CustomConstant.CATEGORY_EDIT_WALLET_LESS);
                transactionDto.setDescription("Chênh lệch chỉnh sửa ví");
                transactionDto.setCreatedDate(DateUtil.convertDateToSeconds(new Date()));

                editWalletPresenter.addNewTransactionByUpdateWallet(transactionDto);
            } else {
                finish();
            }
        } else {
            editWalletAlert.setVisibility(View.VISIBLE);
            editWalletAlert.setText(getString(R.string.updated_failed));
        }
    }

    @Override
    public void showAddNewTransactionByUpdateWallet(Transaction transaction) {
        if (transaction != null) {
            editWalletAlert.setVisibility(View.GONE);
            finish();
        }
    }

    @Override
    public void showDeleteWalletResult(Boolean result) {
        if (result)
            finish();
        else {
            editWalletAlert.setVisibility(View.VISIBLE);
            editWalletAlert.setText(getString(R.string.delete_wallet_failed));

            btnWalletDetailDelete.setEnabled(true);
            btnWalletDetailDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditWalletActivity.this, R.color.red)));

            btnWalletDetailSave.setEnabled(true);
            btnWalletDetailSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditWalletActivity.this, R.color.primary)));
        }
    }

    @Override
    public void showUpdateWalletError() {
        editWalletAlert.setVisibility(View.VISIBLE);
        editWalletAlert.setText(getString(R.string.updated_failed));
    }
}