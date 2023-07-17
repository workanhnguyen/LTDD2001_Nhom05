package com.example.quanlychitieu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quanlychitieu.databinding.ActivityMainBinding;
import com.example.quanlychitieu.fragment.AddExpenseFragment;
import com.example.quanlychitieu.fragment.CategoryFragment;
import com.example.quanlychitieu.fragment.OverViewFragment;
import com.example.quanlychitieu.fragment.SettingFragment;
import com.example.quanlychitieu.fragment.WalletFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new OverViewFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new OverViewFragment());
            } else if (item.getItemId() == R.id.wallet) {
                replaceFragment(new WalletFragment());
            } else if (item.getItemId() == R.id.category) {
                replaceFragment(new CategoryFragment());
            } else if (item.getItemId() == R.id.account) {
                replaceFragment(new SettingFragment());
            } else if (item.getItemId() == R.id.addTransaction){
               replaceFragment(new AddExpenseFragment());
            }
            return true;

        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}