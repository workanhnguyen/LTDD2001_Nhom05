package com.example.quanlychitieu;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.adapters.TransactionAdapter;
import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.fragment.OverViewFragment;
import com.example.quanlychitieu.fragment.SettingFragment;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.utils.LayoutUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Chào Anh Nguyễn!");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new OverViewFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}