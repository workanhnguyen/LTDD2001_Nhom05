package com.example.quanlychitieu.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.fragment.CategoryTypeExpenseFragment;
import com.example.quanlychitieu.fragment.CategoryTypeIncomeFragment;
import com.example.quanlychitieu.fragment.ChooseCategoryTypeExpenseFragment;
import com.example.quanlychitieu.fragment.ChooseCategoryTypeIncomeFragment;

public class ChooseCategoryTypeActivity extends AppCompatActivity {
    TextView chooseCategoryTypeMucChi, chooseCategoryTypeMucThu;

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
        handleChangeCategoryTypeFragment();
    }

    private void handleChangeCategoryTypeFragment() {
        navigateToCategoryExpenseFragment();
        chooseCategoryTypeMucChi.setTextColor(getResources().getColor(R.color.primary));
        chooseCategoryTypeMucThu.setTextColor(getResources().getColor(R.color.black));
        View.OnClickListener textViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the default text color for both TextViews
                chooseCategoryTypeMucChi.setTextColor(getResources().getColor(R.color.black));
                chooseCategoryTypeMucThu.setTextColor(getResources().getColor(R.color.black));

                // Set the clicked TextView's text color to the selected color
                TextView clickedTextView = (TextView) v;
                clickedTextView.setTextColor(getResources().getColor(R.color.primary));

                // Handle navigation based on which TextView is clicked
                if (v.getId() == R.id.txtMucChi) {
                    navigateToCategoryExpenseFragment();
                } else if (v.getId() == R.id.txtMucThu) {
                    navigateToCategoryIncomeFragment();
                }
            }
        };
        chooseCategoryTypeMucChi.setOnClickListener(textViewClickListener);
        chooseCategoryTypeMucThu.setOnClickListener(textViewClickListener);
    }

    private void navigateToCategoryExpenseFragment() {
        ChooseCategoryTypeExpenseFragment chooseCategoryExpenseFragment = new ChooseCategoryTypeExpenseFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view_choose_category, chooseCategoryExpenseFragment)
                .addToBackStack(null) // Optional, for back stack handling
                .commit();
    }
    private void navigateToCategoryIncomeFragment() {
        ChooseCategoryTypeIncomeFragment chooseCategoryIncomeFragment = new ChooseCategoryTypeIncomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view_choose_category, chooseCategoryIncomeFragment)
                .addToBackStack(null) // Optional, for back stack handling
                .commit();
    }

    private void initializeElement() {
        chooseCategoryTypeMucChi = findViewById(R.id.chooseCategoryTypeMucChi);
        chooseCategoryTypeMucThu = findViewById(R.id.chooseCategoryTypeMucThu);
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