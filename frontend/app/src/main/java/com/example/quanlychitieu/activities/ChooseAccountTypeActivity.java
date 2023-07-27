package com.example.quanlychitieu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.ChooseAccountTypeAdapter;
import com.example.quanlychitieu.models.AccountType;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.presenters.ChooseAccountTypePresenter;
import com.example.quanlychitieu.utils.AdapterListener;
import com.example.quanlychitieu.utils.PassDataUtil;
import com.example.quanlychitieu.views.ChooseAccountTypeView;

import org.parceler.Parcels;

import java.util.List;

public class ChooseAccountTypeActivity extends AppCompatActivity implements ChooseAccountTypeView, PassDataUtil {
    RecyclerView chooseAccountTypeList;
    TextView alertLoadingAccountType;
    ChooseAccountTypeAdapter adapter;
    private ChooseAccountTypePresenter chooseAccountTypePresenter;
    AdapterListener adapterListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account_type);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.choose_account_type));
            actionBar.setElevation(0);
        }

        adapterListener = this::finish;
        chooseAccountTypePresenter = new ChooseAccountTypePresenter(this);

        initializeElement();
        loadData();
        handleShowDataToUI();
    }

    private void handleShowDataToUI() {

    }

    private void loadData() {
        alertLoadingAccountType.setText(getString(R.string.loading_data));
        chooseAccountTypePresenter.loadAccountTypeData();
    }

    private void initializeElement() {
        alertLoadingAccountType = findViewById(R.id.alertLoadingAccountType);
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

    @Override
    public void showAccountTypeList(List<AccountType> list) {
        alertLoadingAccountType.setText("");
        adapter = new ChooseAccountTypeAdapter(list);
        adapter.setContext(this);
        adapter.setPassDataUtil(this);
        adapter.setAdapterListener(adapterListener);
        chooseAccountTypeList.setLayoutManager(new LinearLayoutManager(this));
        chooseAccountTypeList.setAdapter(adapter);
    }

    @Override
    public void showAccountTypeError() {
        alertLoadingAccountType.setText(getString(R.string.error_loading_data));
    }

    @Override
    public void passData(Object data) {
        Parcelable parcelable = Parcels.wrap((AccountType) data);

        Intent intent = new Intent();
        intent.putExtra("accountType", parcelable);
        setResult(RESULT_OK, intent);
        finish();
    }
}