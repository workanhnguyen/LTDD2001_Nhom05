package com.example.quanlychitieu.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.ViewPagerAdapter;
import com.example.quanlychitieu.fragment.CustomTimeSatisticFragment;
import com.example.quanlychitieu.fragment.DayStatisticFragment;
import com.example.quanlychitieu.fragment.MonthStatisticFragment;
import com.google.android.material.tabs.TabLayout;

public class StatisticFilterActivity extends AppCompatActivity {
    TabLayout statisticTabLayout;
    ViewPager statisticViewPager;

    ViewPagerAdapter viewPagerAdapter;

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
        setTabLayoutAdapter();
    }

    private void setTabLayoutAdapter() {
        statisticTabLayout.setupWithViewPager(statisticViewPager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new DayStatisticFragment(), getString(R.string.date));
        viewPagerAdapter.addFragment(new MonthStatisticFragment(), getString(R.string.month));
        viewPagerAdapter.addFragment(new CustomTimeSatisticFragment(), getString(R.string.custom_time));

        statisticViewPager.setAdapter(viewPagerAdapter);
    }

    private void initializeElement() {
        statisticTabLayout = findViewById(R.id.statisticTabLayout);
        statisticViewPager = findViewById(R.id.statisticViewPager);
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