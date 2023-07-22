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

public class StatisticFilterActivity extends AppCompatActivity {
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
                intent.putExtra("test", filterLastMonth.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        filterThisMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticFilterActivity.this, MainActivity.class);
                intent.putExtra("test", filterThisMonth.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        filterAnotherMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticFilterActivity.this, MainActivity.class);
                intent.putExtra("test", filterAnotherMonth.getText().toString());
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
}