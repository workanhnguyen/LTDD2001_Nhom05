package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.ChooseWalletAdapter;
import com.example.quanlychitieu.adapters.WalletAdapter;
import com.example.quanlychitieu.models.AccountType;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.presenters.ChooseWalletPresenter;
import com.example.quanlychitieu.sampledatas.WalletData;
import com.example.quanlychitieu.utils.AdapterListener;
import com.example.quanlychitieu.utils.PassDataUtil;
import com.example.quanlychitieu.views.ChooseWalletView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ChooseWalletActivity extends AppCompatActivity implements ChooseWalletView, PassDataUtil {
    RecyclerView chooseWalletList;
    TextView alertLoadingWallet;
    ChooseWalletAdapter adapter;
    List<Wallet> list = new ArrayList<>();
    ChooseWalletPresenter chooseWalletPresenter;
    AdapterListener adapterListener;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_wallet);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.choose_wallet);
            actionBar.setElevation(0);
        }

        adapterListener = this::finish;
        chooseWalletPresenter = new ChooseWalletPresenter(this);
        sharedPreferences = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);

        initializeElement();
        loadData();
        handleShowDataToUI();
    }

    private void handleShowDataToUI() {
        adapter = new ChooseWalletAdapter(list);
        adapter.setContext(ChooseWalletActivity.this);
        chooseWalletList.setLayoutManager(new LinearLayoutManager(ChooseWalletActivity.this));
        chooseWalletList.setAdapter(adapter);
    }

    private void loadData() {
        alertLoadingWallet.setText(getString(R.string.loading_data));
        chooseWalletPresenter.loadWalletsData(sharedPreferences.getInt("id", 1));
    }

    private void initializeElement() {
        alertLoadingWallet = findViewById(R.id.alertLoadingWallet);
        chooseWalletList = findViewById(R.id.chooseWalletList);
    }

    private void populateListView(List<Wallet> list) {
        adapter = new ChooseWalletAdapter(list);
        adapter.setContext(this);
        adapter.setAdapterListener(adapterListener);
        adapter.setPassDataUtil(this);
        chooseWalletList.setLayoutManager(new LinearLayoutManager(this));
        chooseWalletList.setAdapter(adapter);
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
    public void passData(Object data) {
        Parcelable parcelable = Parcels.wrap((Wallet) data);

        Intent intent = new Intent();
        intent.putExtra("wallet", parcelable);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showWalletList(List<Wallet> list) {
        if (!list.isEmpty()) {
            alertLoadingWallet.setText("");
            populateListView(list);
        } else {
            alertLoadingWallet.setText(getString(R.string.no_data));
        }
    }

    @Override
    public void showWalletError() {
        alertLoadingWallet.setText(getString(R.string.error_loading_data));
    }
}