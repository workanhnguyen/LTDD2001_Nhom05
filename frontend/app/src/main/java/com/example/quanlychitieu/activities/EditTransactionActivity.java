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
import com.example.quanlychitieu.fragment.CalendarFragment;
import com.example.quanlychitieu.dtos.WalletDto;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.presenters.EditTransactionPresenter;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.utils.DateUtil;
import com.example.quanlychitieu.views.EditTransactionView;

import org.parceler.Parcels;

import java.text.ParseException;
import java.util.Objects;

public class EditTransactionActivity extends AppCompatActivity implements EditTransactionView, CalendarFragment.DateTimeListener {
    private static final int REQUEST_CODE_SELECT_CATEGORY = 1;
    private static final int REQUEST_CODE_SELECT_WALLET = 2;
    EditText editTransactionBalance, editTransactionDescription;
    LinearLayout linearLayoutEditTransactionCategoryType, linearLayoutEditTransactionWallet, linearLayoutCalendar;
    Button editTransactionDelete, editTransactionSave;
    ImageView editTransactionCategoryTypeImage, editTransactionWalletImage;
    TextView editTransactionCategoryTypeName, editTransactionWalletName, editTransactionAlert, editTransactionTime, editTransactionReadOnly, txtCalendarDate;

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

        handleShowCalendar();
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
        editTransactionSave.setOnClickListener(v -> {

            editTransactionAlert.setVisibility(View.GONE);
            editTransactionAlert.setText("");

            String transactionBalance = editTransactionBalance.getText().toString().trim().replace(".", "");
            String transactionDescription = editTransactionDescription.getText().toString().trim();
            String transactionTime = editTransactionTime.getText().toString();

            String alertString = validateTransactionInput(transactionBalance, transactionDescription, transactionTime, transaction.getWallet(), transaction.getCategoryType());

            if (alertString.trim().isEmpty()) {
                editTransactionSave.setEnabled(false);
                editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.dark_grey)));
                editTransactionSave.setText(getString(R.string.saving));

                editTransactionDelete.setEnabled(false);
                editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.dark_grey)));
                try {
                    TransactionDto transactionDto = new TransactionDto();
                    transactionDto.setTotal(Long.parseLong(transactionBalance));
                    transactionDto.setWalletId(wallet.getId());
                    transactionDto.setCategoryTypeId(categoryType.getId());
                    transactionDto.setDescription(transactionDescription);
                    transactionDto.setCreatedDate(DateUtil.convertDateToSeconds(DateUtil.parseStringToDate(transactionTime, CustomConstant.DATE_FORMAT_dd_MM_yyyy_hh_mm_a)));

                    editTransactionPresenter.updateTransaction(transaction.getId(), transactionDto);
                } catch (ParseException e) {
                    editTransactionAlert.setVisibility(View.VISIBLE);
                    editTransactionAlert.setText(getString(R.string.time_error));

                    editTransactionSave.setEnabled(true);
                    editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.primary)));
                    editTransactionSave.setText(getString(R.string.save));

                    editTransactionDelete.setEnabled(true);
                    editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.red)));
                }
            } else {
                editTransactionAlert.setVisibility(View.VISIBLE);
                editTransactionAlert.setText(alertString);

                editTransactionSave.setEnabled(true);
                editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.primary)));
                editTransactionSave.setText(getString(R.string.save));

                editTransactionDelete.setEnabled(true);
                editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.red)));
            }
        });
    }

    private String validateTransactionInput(String transactionBalance, String transactionDescription, String transactionTime, Wallet wallet, CategoryType categoryType) {
        if (transactionBalance.isEmpty() || transactionDescription.isEmpty() || transactionTime.isEmpty() || wallet == null || categoryType == null)
            return getString(R.string.not_empty_field);
        return "";
    }

    private void handleShowCalendar(){
        linearLayoutCalendar.setOnClickListener(v -> {
            CalendarFragment calendarDialogFragment = new CalendarFragment();
            calendarDialogFragment.show(getSupportFragmentManager(), "calendar_dialog");
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
                .load(transaction.getWallet().getAccountType().getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(editTransactionWalletImage);
        editTransactionCategoryTypeName.setText(transaction.getCategoryType().getName());
        editTransactionWalletName.setText(transaction.getWallet().getName());
        editTransactionDescription.setText(transaction.getDescription());
        editTransactionTime.setText(DateUtil.convertSecondsToFormattedDate(transaction.getCreatedDate(), CustomConstant.DATE_FORMAT_dd_MM_yyyy_hh_mm_a));

        if (Objects.equals(transaction.getCategoryType().getId(), CustomConstant.CATEGORY_EDIT_WALLET_GREATER)
                || Objects.equals(transaction.getCategoryType().getId(), CustomConstant.CATEGORY_EDIT_WALLET_LESS)) {
            disableEditTransactionView();
        }
    }

    private void disableEditTransactionView() {
        editTransactionBalance.setEnabled(false);
        editTransactionDescription.setEnabled(false);
        editTransactionTime.setEnabled(false);
        editTransactionSave.setVisibility(View.GONE);
        editTransactionReadOnly.setVisibility(View.VISIBLE);
        editTransactionReadOnly.setText(getString(R.string.read_only_transaction));

        linearLayoutEditTransactionCategoryType.setEnabled(false);
        linearLayoutEditTransactionWallet.setEnabled(false);
    }

    private void initializeElement() {

        editTransactionBalance = findViewById(R.id.editTransactionBalance);
        editTransactionDescription = findViewById(R.id.editTransactionDescription);
        linearLayoutCalendar = findViewById(R.id.calendar);
        editTransactionTime = findViewById(R.id.editTransactionTime);
        editTransactionReadOnly = findViewById(R.id.editTransactionReadOnly);

        linearLayoutEditTransactionCategoryType = findViewById(R.id.linearLayoutEditTransactionCategoryType);
        linearLayoutEditTransactionWallet = findViewById(R.id.linearLayoutEditTransactionWallet);

        editTransactionDelete = findViewById(R.id.editTransactionDelete);
        editTransactionSave = findViewById(R.id.editTransactionSave);

        editTransactionCategoryTypeName = findViewById(R.id.editTransactionCategoryTypeName);
        editTransactionWalletName = findViewById(R.id.editTransactionWalletName);

        editTransactionWalletImage = findViewById(R.id.editTransactionWalletImage);
        editTransactionCategoryTypeImage = findViewById(R.id.editTransactionCategoryTypeImage);
        editTransactionTime = findViewById(R.id.editTransactionTime);

        editTransactionAlert = findViewById(R.id.editTransactionAlert);
    }

    private void loadTransactionData() {
        Parcelable parcelable = getIntent().getParcelableExtra("transaction");
        transaction = Parcels.unwrap(parcelable);
        wallet = transaction.getWallet();
        categoryType = transaction.getCategoryType();
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
//            transaction.setCategoryType(categoryType);

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
//            transaction.setWallet(wallet);

            RequestOptions requestOptionsWallet = new RequestOptions()
                    .placeholder(R.drawable.app_icon_background)
                    .error(R.drawable.app_icon_background);

            Glide.with(EditTransactionActivity.this).load(wallet.getAccountType().getImageLink())
                    .apply(requestOptionsWallet).diskCacheStrategy(DiskCacheStrategy.ALL).into(editTransactionWalletImage);

            editTransactionWalletName.setText(wallet.getName());
        }
    }

    @Override
    public void showUpdatedTransaction(Transaction updatedTransaction) {
        if (transaction != null) {
            long updatedBalance = updatedTransaction.getTotal();
            long currentBalance = transaction.getTotal();

            WalletDto walletDto = new WalletDto();
            WalletDto unChooseWalletDto = new WalletDto();

            if (Objects.equals(transaction.getWallet().getId(), updatedTransaction.getWallet().getId())) {

                if (transaction.getCategoryType().getCategoryRoot().getType().equals(updatedTransaction.getCategoryType().getCategoryRoot().getType())) {
                    if (updatedTransaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE)) {
                        walletDto.setBalance(updatedTransaction.getWallet().getBalance() + currentBalance - updatedBalance);
                    } else if (updatedTransaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_INCOME)) {
                        walletDto.setBalance(updatedTransaction.getWallet().getBalance() - currentBalance + updatedBalance);
                    }
                } else {
                    if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE)
                            && updatedTransaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_INCOME)) {
                        walletDto.setBalance(updatedTransaction.getWallet().getBalance() + currentBalance + updatedBalance);
                    } else if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_INCOME)
                            && updatedTransaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE)) {
                        walletDto.setBalance(updatedTransaction.getWallet().getBalance() - currentBalance - updatedBalance);
                    }
                }

                editTransactionPresenter.updateWalletBalance(updatedTransaction.getWallet().getId(), walletDto);
            } else {

                if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE)) {
                    unChooseWalletDto.setBalance(transaction.getWallet().getBalance() + currentBalance);
                } else if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_INCOME)) {
                    unChooseWalletDto.setBalance(transaction.getWallet().getBalance() - currentBalance);
                }

                if (transaction.getCategoryType().getCategoryRoot().getType().equals(updatedTransaction.getCategoryType().getCategoryRoot().getType())) {
                    if (updatedTransaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE)) {
                        walletDto.setBalance(updatedTransaction.getWallet().getBalance() - updatedBalance);
                    } else if (updatedTransaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_INCOME)) {
                        walletDto.setBalance(updatedTransaction.getWallet().getBalance() + updatedBalance);
                    }
                } else {
                    if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE)
                            && updatedTransaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_INCOME)) {
                        walletDto.setBalance(updatedTransaction.getWallet().getBalance() + updatedBalance);
                    } else if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_INCOME)
                            && updatedTransaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE)) {
                        walletDto.setBalance(updatedTransaction.getWallet().getBalance() - updatedBalance);
                    }
                }

                editTransactionPresenter.updateWalletBalance(transaction.getWallet().getId(), unChooseWalletDto);
                editTransactionPresenter.updateWalletBalance(updatedTransaction.getWallet().getId(), walletDto);
            }

        } else {
            editTransactionAlert.setVisibility(View.VISIBLE);
            editTransactionAlert.setText(getString(R.string.unknown_error));

            editTransactionSave.setEnabled(true);
            editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.primary)));
            editTransactionSave.setText(getString(R.string.save));

            editTransactionDelete.setEnabled(true);
            editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.red)));
        }
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
            editTransactionAlert.setText("");

            editTransactionSave.setEnabled(true);
            editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.primary)));
            editTransactionSave.setText(getString(R.string.save));

            editTransactionDelete.setEnabled(true);
            editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.red)));

            finish();
        }
    }

    @Override
    public void showErrorUpdatingTransaction() {
        editTransactionAlert.setVisibility(View.VISIBLE);
        editTransactionAlert.setText(getString(R.string.updated_failed));

        editTransactionSave.setEnabled(true);
        editTransactionSave.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.primary)));
        editTransactionSave.setText(getString(R.string.save));

        editTransactionDelete.setEnabled(true);
        editTransactionDelete.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(EditTransactionActivity.this, R.color.red)));
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

    @Override
    public void onDateTimeSelected(String selectedDateTime) {
        editTransactionTime.setText(selectedDateTime);
    }
}