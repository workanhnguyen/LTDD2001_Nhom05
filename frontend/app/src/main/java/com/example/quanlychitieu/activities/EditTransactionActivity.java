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
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.utils.CommonUtil;

import org.parceler.Parcels;

public class EditTransactionActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    Transaction transaction;
    EditText editTransactionBalance, editTransactionDescription;
    LinearLayout linearLayoutEditTransactionCategoryType, linearLayoutEditTransactionWallet;
    Button editTransactionDelete, editTransactionSave;
    ImageView editTransactionCategoryImage, editTransactionWalletImage;
    TextView editTransactionCategoryName, editTransactionWalletName;

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
                startActivity(intent);
            }
        });
    }

    private void handleSwitchToChooseCategoryType() {
        linearLayoutEditTransactionCategoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTransactionActivity.this, ChooseCategoryTypeActivity.class);
                startActivity(intent);
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
                        // Handle deleting wallet here

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

    private void handleSaveTransaction() {
        editTransactionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void handleShowDataToUI() {
        editTransactionBalance.setText(CommonUtil.getMoneyFormat(transaction.getTotal()).substring(0, CommonUtil.getMoneyFormat(transaction.getTotal()).length() - 1));

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.app_icon_background) // Replace with your placeholder image resource
                .error(R.drawable.app_icon_background); // Replace with your error image resource
        Glide.with(EditTransactionActivity.this)
                .load(transaction.getCategoryType().getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(editTransactionCategoryImage);
        Glide.with(EditTransactionActivity.this)
                .load(transaction.getWallet().getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(editTransactionWalletImage);
        editTransactionCategoryName.setText(transaction.getCategoryType().getName());
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

        editTransactionCategoryName = findViewById(R.id.editTransactionCategoryName);
        editTransactionWalletName = findViewById(R.id.editTransactionWalletName);

        editTransactionWalletImage = findViewById(R.id.editTransactionWalletImage);
        editTransactionCategoryImage = findViewById(R.id.editTransactionCategoryImage);
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
}