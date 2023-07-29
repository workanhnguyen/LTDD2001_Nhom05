package com.example.quanlychitieu.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.adapters.AccountRootAdapter;
import com.example.quanlychitieu.models.AccountRoot;

import java.util.ArrayList;
import java.util.List;

public class ChooseAccountRootActivity extends AppCompatActivity {
    RecyclerView accountRootList;
    List<AccountRoot> accountRoots = new ArrayList<>();
    AccountRootAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account_root);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.choose_account_root);
            actionBar.setElevation(0);
        }

        initializeElement();
        loadAccountRootData();
        handleShowDataToUI();
    }

    private void handleShowDataToUI() {
        adapter = new AccountRootAdapter(accountRoots);
        adapter.setContext(this);
        accountRootList.setLayoutManager(new LinearLayoutManager(this));
        accountRootList.setAdapter(adapter);
    }
    private void loadAccountRootData() {
        accountRoots.add(new AccountRoot(1, "Phổ biến", ""));
        accountRoots.add(new AccountRoot(2, "Tài khoản ngân hàng", ""));
        accountRoots.add(new AccountRoot(3, "Ví điện tử", ""));
    }
    private void initializeElement() {
        accountRootList = findViewById(R.id.accountRootList);
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