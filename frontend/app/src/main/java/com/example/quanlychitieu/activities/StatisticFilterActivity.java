package com.example.quanlychitieu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlychitieu.MainActivity;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.utils.CustomConstant;

public class StatisticFilterActivity extends AppCompatActivity implements MonthYearPickerActivity.OnDateSetListener {
    TextView filterLastMonth, filterThisMonth, filterAnotherMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_filter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.data_setting);
            actionBar.setElevation(0);
        }

        initializeElement();
        handleFilterItemClicked();
    }

    private void handleFilterItemClicked() {
        filterLastMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticFilterActivity.this, MainActivity.class);
                intent.putExtra("sts_filter", CustomConstant.FILTER_STATISTIC_LAST_MONTH);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        filterThisMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticFilterActivity.this, MainActivity.class);
                intent.putExtra("sts_filter", CustomConstant.FILTER_STATISTIC_THIS_MONTH);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        filterAnotherMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonthYearPickerDialog();
                Intent intent = new Intent(StatisticFilterActivity.this, MainActivity.class);
                intent.putExtra("sts_filter", CustomConstant.FILTER_STATISTIC_OTHER_MONTH);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initializeElement() {
        filterLastMonth = findViewById(R.id.filterLastMonth);
        filterThisMonth = findViewById(R.id.filterThisMonth);
        filterAnotherMonth = findViewById(R.id.filterAnotherMonth);
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


    private void showMonthYearPickerDialog() {
        MonthYearPickerActivity dialog = new MonthYearPickerActivity();
        dialog.setOnDateSetListener(this);
        dialog.show(getSupportFragmentManager(), "MonthYearPickerDialog");
    }

    @Override
    public void onDateSet(int year, int month) {
//        String selectedDate = String.format("Selected Date: %02d/%04d", month, year);
//        // For example, update the text of a TextView with the selected date.
//        selectedDateTextView.setText(selectedDate);
    }


//    @Override
//    public void onDateSet(int year, int month) {
//        String selectedDate = String.format("Selected Date: %02d/%04d", month, year);
//        selectedDateTextView.setText(selectedDate);
//    }
}