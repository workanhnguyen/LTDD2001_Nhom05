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

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.WalletDto;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.presenters.EditTransactionPresenter;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.views.EditTransactionView;

import org.parceler.Parcels;

public class EditTransactionActivity extends AppCompatActivity implements EditTransactionView {
    private static final int REQUEST_CODE_SELECT_CATEGORY = 1;
    private static final int REQUEST_CODE_SELECT_WALLET = 2;
    EditText editTransactionBalance, editTransactionDescription;
    LinearLayout linearLayoutEditTransactionCategoryType, linearLayoutEditTransactionWallet;
    Button editTransactionDelete, editTransactionSave;
    ImageView editTransactionCategoryTypeImage, editTransactionWalletImage;
    TextView editTransactionCategoryTypeName, editTransactionWalletName, editTransactionAlert;

    // ----------------------------------
    CategoryType categoryType;
    Wallet wallet;
    Transaction transaction;
    EditTransactionPresenter editTransactionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.edit_transaction);
            actionBar.setElevation(0);
        }

        editTransactionPresenter = new EditTransactionPresenter(this);

        initializeElement();

        loadTransactionData();
        handleShowDataToUI();

        handleSwitchToChooseCategoryType();
        handleSwitchToChooseWallet();

        handleSaveTransaction();
        handleDeleteTransaction();
    }

    private void handleSwitchToChooseWallet() {
        linearLayoutEditTransactionWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTransactionActivity.this, ChooseWalletActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_WALLET);
            }
        });
    }

    private void handleSwitchToChooseCategoryType() {
        linearLayoutEditTransactionCategoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTransactionActivity.this, ChooseCategoryTypeActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_CATEGORY);
            }
        });
    }

    private void handleDeleteTransaction() {
        editTransactionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditTransactionActivity.this);
                builder.setTitle(getString(R.string.delete_transaction));
                builder.setMessage(getString(R.string.do_you_want_to_delete_this_transaction));
                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTransactionAlert.setVisibility(View.GONE);

                        editTransactionDelete.setEnabled(false);
                        editTransactionDelete.setText(getString(R.string.deleting));
                        editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.dark_grey)));

                        editTransactionSave.setEnabled(false);
                        editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.dark_grey)));

                        editTransactionPresenter.deleteTransaction(transaction.getId());
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

    private void handleSaveTransaction() {
        editTransactionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void handleShowDataToUI() {
        editTransactionBalance.setText(CommonUtil.getMoneyFormat(transaction.getTotal()).substring(0, CommonUtil.getMoneyFormat(transaction.getTotal()).length() - 2));

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.app_icon_background) // Replace with your placeholder image resource
                .error(R.drawable.app_icon_background); // Replace with your error image resource
        Glide.with(EditTransactionActivity.this)
                .load(transaction.getCategoryType().getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(editTransactionCategoryTypeImage);
        Glide.with(EditTransactionActivity.this)
                .load(transaction.getWallet().getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(editTransactionWalletImage);
        editTransactionCategoryTypeName.setText(transaction.getCategoryType().getName());
        editTransactionWalletName.setText(transaction.getWallet().getName());
        editTransactionDescription.setText(transaction.getDescription());
    }

    private void initializeElement() {
        editTransactionBalance = findViewById(R.id.editTransactionBalance);
        editTransactionDescription = findViewById(R.id.editTransactionDescription);

        linearLayoutEditTransactionCategoryType = findViewById(R.id.linearLayoutEditTransactionCategoryType);
        linearLayoutEditTransactionWallet = findViewById(R.id.linearLayoutEditTransactionWallet);

        editTransactionDelete = findViewById(R.id.editTransactionDelete);
        editTransactionSave = findViewById(R.id.editTransactionSave);

        editTransactionCategoryTypeName = findViewById(R.id.editTransactionCategoryTypeName);
        editTransactionWalletName = findViewById(R.id.editTransactionWalletName);

        editTransactionWalletImage = findViewById(R.id.editTransactionWalletImage);
        editTransactionCategoryTypeImage = findViewById(R.id.editTransactionCategoryTypeImage);

        editTransactionAlert = findViewById(R.id.editTransactionAlert);
    }

    private void loadTransactionData() {
        Parcelable parcelable = getIntent().getParcelableExtra("transaction");
        transaction = Parcels.unwrap(parcelable);
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
        if (requestCode == REQUEST_CODE_SELECT_CATEGORY && resultCode == RESULT_OK) {

            // Handle to show choosed category
            assert data != null;
            Parcelable parcelableCategoryType = data.getParcelableExtra("categoryType");
            categoryType = Parcels.unwrap(parcelableCategoryType);

            RequestOptions requestOptionsCategoryType = new RequestOptions()
                    .placeholder(R.drawable.app_icon_background)
                    .error(R.drawable.app_icon_background);

            Glide.with(EditTransactionActivity.this).load(categoryType.getImageLink())
                    .apply(requestOptionsCategoryType).diskCacheStrategy(DiskCacheStrategy.ALL).into(editTransactionCategoryTypeImage);

            editTransactionCategoryTypeName.setText(categoryType.getName());
        }

         else if (requestCode == REQUEST_CODE_SELECT_WALLET && resultCode == RESULT_OK) {
            // Handle to show choosed wallet
            assert data != null;
            Parcelable parcelableWallet = data.getParcelableExtra("wallet");
            wallet = Parcels.unwrap(parcelableWallet);

            RequestOptions requestOptionsWallet = new RequestOptions()
                    .placeholder(R.drawable.app_icon_background)
                    .error(R.drawable.app_icon_background);

            Glide.with(EditTransactionActivity.this).load(wallet.getImageLink())
                    .apply(requestOptionsWallet).diskCacheStrategy(DiskCacheStrategy.ALL).into(editTransactionWalletImage);

            editTransactionWalletName.setText(wallet.getName());
        }
    }

    @Override
    public void showUpdatedTransaction(Transaction transaction) {

    }

    @Override
    public void showDeleteTransactionResult(boolean isDeleted) {
        if (isDeleted) {
            long updatedBalance = 0;
            long transactionBalance = Long.parseLong(editTransactionBalance.getText().toString().replace(".", "").trim());
            if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE))
                updatedBalance = transaction.getWallet().getBalance() + transactionBalance;
            else if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_INCOME))
                updatedBalance = transaction.getWallet().getBalance() - transactionBalance;

            WalletDto walletDto = new WalletDto();
            walletDto.setBalance(updatedBalance);
            editTransactionPresenter.updateWalletBalance(transaction.getWallet().getId(), walletDto);
        }
    }

    @Override
    public void showUpdatedWalletBalance(Wallet wallet) {
        if (wallet != null) {
            editTransactionAlert.setVisibility(View.GONE);

            editTransactionDelete.setEnabled(true);
            editTransactionDelete.setText(getString(R.string.delete));
            editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.red)));

            editTransactionSave.setEnabled(true);
            editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.primary)));

            finish();
        }
    }

    @Override
    public void showErrorUpdatingTransaction() {
        editTransactionAlert.setVisibility(View.VISIBLE);
        editTransactionAlert.setText(getString(R.string.updated_failed));
    }

    @Override
    public void showErrorDeletingTransaction() {
        editTransactionAlert.setVisibility(View.VISIBLE);
        editTransactionAlert.setText(getString(R.string.delete_transaction_failed));

        editTransactionAlert.setVisibility(View.GONE);

        editTransactionDelete.setEnabled(true);
        editTransactionDelete.setText(getString(R.string.delete));
        editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.red)));

        editTransactionSave.setEnabled(true);
        editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.primary)));
    }
}