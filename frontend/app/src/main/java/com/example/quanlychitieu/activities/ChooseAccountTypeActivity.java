package com.example.quanlychitieu.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.adapters.ChooseAccountTypeAdapter;
import com.example.quanlychitieu.models.AccountType;

import java.util.List;

public class ChooseAccountTypeActivity extends AppCompatActivity {
    RecyclerView chooseAccountTypeList;
    List<AccountType> list;
    ChooseAccountTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account_type);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("<Sửa thành tên của account root>");
            actionBar.setElevation(0);
        }

        initializeElement();
        loadListAccountTypeData();
        handleShowDataToUI();
    }

    private void handleShowDataToUI() {
        adapter = new ChooseAccountTypeAdapter(list);
        adapter.setContext(this);
        chooseAccountTypeList.setLayoutManager(new LinearLayoutManager(this));
        chooseAccountTypeList.setAdapter(adapter);
    }

    private void loadListAccountTypeData() {
    }

    private void initializeElement() {
        chooseAccountTypeList = findViewById(R.id.chooseAccountTypeList);
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