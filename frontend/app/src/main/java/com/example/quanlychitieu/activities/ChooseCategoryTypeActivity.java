package com.example.quanlychitieu.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.ChooseCategoryTypeAdapter;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.sampledatas.CategoryData;

import java.util.List;

public class ChooseCategoryTypeActivity extends AppCompatActivity {
    TextView chooseCategoryTypeExpense, chooseCategoryTypeIncome;
    RecyclerView chooseCategoryTypeList;
    ChooseCategoryTypeAdapter adapter;
    List<CategoryType> expenseCategories = CategoryData.getExpenseCategoryTypeList();
    List<CategoryType> incomeCategories = CategoryData.getIncomeCategoryTypeList();

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

        initializeElement();
        loadCategoryTypeData(expenseCategories);
        handleShowCategoryTypeToUI();
    }

    private void loadCategoryTypeData(List<CategoryType> list) {
        adapter = new ChooseCategoryTypeAdapter(list);
        adapter.setContext(ChooseCategoryTypeActivity.this);
        chooseCategoryTypeList.setAdapter(adapter);
        chooseCategoryTypeList.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("ResourceAsColor")
    private void handleShowCategoryTypeToUI() {
        chooseCategoryTypeExpense.setTextColor(R.color.green);
        chooseCategoryTypeIncome.setTextColor(R.color.black);

        chooseCategoryTypeExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCategoryTypeIncome.setTextColor(R.color.black);
                chooseCategoryTypeExpense.setTextColor(Color.RED);

                loadCategoryTypeData(expenseCategories);
            }
        });

        chooseCategoryTypeIncome.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
//                navigateToChooseCategoryTypeFragment(new ChooseCategoryTypeIncomeFragment());
                chooseCategoryTypeIncome.setTextColor(Color.RED);
                chooseCategoryTypeExpense.setTextColor(R.color.black);

                loadCategoryTypeData(incomeCategories);
            }
        });
    }

    private void initializeElement() {
        chooseCategoryTypeExpense = findViewById(R.id.chooseCategoryTypeExpense);
        chooseCategoryTypeIncome = findViewById(R.id.chooseCategoryTypeIncome);
        chooseCategoryTypeList = findViewById(R.id.chooseCategoryTypeList);
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