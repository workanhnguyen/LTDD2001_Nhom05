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
import com.example.quanlychitieu.adapters.ChooseCategoryTypeAdapter;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.presenters.ChooseCategoryTypePresenter;
import com.example.quanlychitieu.utils.AdapterListener;
import com.example.quanlychitieu.utils.PassDataUtil;
import com.example.quanlychitieu.views.ChooseCategoryTypeView;

import org.parceler.Parcels;

import java.util.List;

public class ChooseCategoryTypeActivity extends AppCompatActivity implements ChooseCategoryTypeView, PassDataUtil {
    TextView chooseCategoryTypeExpense, chooseCategoryTypeIncome, alertLoadingCategoryType;
    RecyclerView chooseCategoryTypeList;
    ChooseCategoryTypeAdapter adapter;

    private ChooseCategoryTypePresenter chooseCategoryTypePresenter;
    AdapterListener adapterListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category_type);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.choose_category_type);
            actionBar.setElevation(0);
        }

        adapterListener = this::finish;
        chooseCategoryTypePresenter = new ChooseCategoryTypePresenter(this);

        initializeElement();
        loadExpenseCategoryTypeData();
        handleShowCategoryTypeToUI();
    }

    private void loadExpenseCategoryTypeData() {
        alertLoadingCategoryType.setText(getString(R.string.loading_data));
        chooseCategoryTypePresenter.loadExpenseCategories();
    }
    private void loadIncomeCategoryTypeData() {
        alertLoadingCategoryType.setText(getString(R.string.loading_data));
        chooseCategoryTypePresenter.loadIncomeCategories();
    }
    private void handleShowCategoryTypeToUI() {
        chooseCategoryTypeExpense.setTextColor(getResources().getColor(R.color.primary));
        chooseCategoryTypeIncome.setTextColor(getResources().getColor(R.color.black));

        chooseCategoryTypeExpense.setOnClickListener(v -> {
            chooseCategoryTypeIncome.setTextColor(getResources().getColor(R.color.black));
            chooseCategoryTypeExpense.setTextColor(getResources().getColor(R.color.primary));

            loadExpenseCategoryTypeData();
        });

        chooseCategoryTypeIncome.setOnClickListener(v -> {
            chooseCategoryTypeIncome.setTextColor(getResources().getColor(R.color.primary));
            chooseCategoryTypeExpense.setTextColor(getResources().getColor(R.color.black));

            loadIncomeCategoryTypeData();
        });
    }

    private void initializeElement() {
        chooseCategoryTypeExpense = findViewById(R.id.chooseCategoryTypeExpense);
        chooseCategoryTypeIncome = findViewById(R.id.chooseCategoryTypeIncome);
        chooseCategoryTypeList = findViewById(R.id.chooseCategoryTypeList);

        alertLoadingCategoryType = findViewById(R.id.alertLoadingCategoryType);
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
    public void showExpenseCategories(List<CategoryType> list) {
        alertLoadingCategoryType.setText("");

        adapter = new ChooseCategoryTypeAdapter(list);
        adapter.setContext(ChooseCategoryTypeActivity.this);
        adapter.setAdapterListener(adapterListener);
        adapter.setPassData(this);

        chooseCategoryTypeList.setLayoutManager(new LinearLayoutManager(ChooseCategoryTypeActivity.this));
        chooseCategoryTypeList.setAdapter(adapter);
    }

    @Override
    public void showIncomeCategories(List<CategoryType> list) {
        alertLoadingCategoryType.setText("");

        adapter = new ChooseCategoryTypeAdapter(list);
        adapter.setContext(ChooseCategoryTypeActivity.this);
        adapter.setAdapterListener(adapterListener);
        adapter.setPassData(this);

        chooseCategoryTypeList.setLayoutManager(new LinearLayoutManager(ChooseCategoryTypeActivity.this));
        chooseCategoryTypeList.setAdapter(adapter);
    }

    @Override
    public void showError() {
        alertLoadingCategoryType.setText(getString(R.string.error_loading_data));
    }

//    @Override
//    public void passCategoryTypeData(CategoryType categoryType) {
////        Parcelable parcelable = Parcels.wrap(categoryType);
////
////        Intent intent = new Intent();
////        intent.putExtra("categoryType", parcelable);
////        setResult(RESULT_OK, intent);
////        finish();
//    }

    @Override
    public void passData(Object data) {
        Parcelable parcelable = Parcels.wrap((CategoryType) data);

        Intent intent = new Intent();
        intent.putExtra("categoryType", parcelable);
        setResult(RESULT_OK, intent);
        finish();
    }
}