package com.example.quanlychitieu.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.adapters.ChooseWalletAdapter;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.sampledatas.WalletData;

import java.util.ArrayList;
import java.util.List;

public class ChooseWalletActivity extends AppCompatActivity {
    RecyclerView chooseWalletList;
    ChooseWalletAdapter adapter;
    List<Wallet> list = new ArrayList<>();

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

        initializeElement();
        loadListWalletData();
        handleShowDataToUI();
    }

    private void handleShowDataToUI() {
        adapter = new ChooseWalletAdapter(list);
        adapter.setContext(ChooseWalletActivity.this);
        chooseWalletList.setLayoutManager(new LinearLayoutManager(ChooseWalletActivity.this));
        chooseWalletList.setAdapter(adapter);
    }

    private void loadListWalletData() {
        list = WalletData.getAllWallets();
    }

    private void initializeElement() {
        chooseWalletList = findViewById(R.id.chooseWalletList);
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