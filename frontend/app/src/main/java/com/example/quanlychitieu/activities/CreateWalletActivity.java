package com.example.quanlychitieu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.models.AccountType;

import org.parceler.Parcels;

public class CreateWalletActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SELECT_ACCOUNT = 1;
    LinearLayout linearLayoutCreateWalletChooseAccountRoot;
    Button btnConfrimCreateWallet;
    ImageView createWalletAccountTypeImage;
    TextView createWalletAccountTypeName;
    // -------------------------------------------
    AccountType accountType;

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
}