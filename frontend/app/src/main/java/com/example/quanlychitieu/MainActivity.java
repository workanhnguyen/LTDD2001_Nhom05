package com.example.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quanlychitieu.databinding.ActivityMainBinding;
import com.example.quanlychitieu.fragment.CategoryTypeFragment;
import com.example.quanlychitieu.fragment.CreateTransactionFragment;
import com.example.quanlychitieu.fragment.OverViewFragment;
import com.example.quanlychitieu.fragment.SettingFragment;
import com.example.quanlychitieu.fragment.WalletFragment;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handleChangeFragment();
    }

    private void handleChangeFragment() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new OverViewFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new OverViewFragment());
            } else if (item.getItemId() == R.id.wallet) {
                replaceFragment(new WalletFragment());
            } else if (item.getItemId() == R.id.category) {
                replaceFragment(new CategoryTypeFragment());
            } else if (item.getItemId() == R.id.account) {
                replaceFragment(new SettingFragment());
            } else if (item.getItemId() == R.id.addTransaction){
                replaceFragment(new CreateTransactionFragment());
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

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(this, getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}