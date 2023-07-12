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

    LayoutUtil util = new LayoutUtil();

    // UI variables
//    TextView tvTotalBalance1;
//    RecyclerView transactionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Chào Anh Nguyễn!");
//        util.customActionBar("Chào Anh Nguyễn!");
//        util.changeFragment(R.id.fragmentContainerView, new SettingFragment());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new OverViewFragment())
                .commit();

//        initializeElement();
//        loadTransactionData();
    }

//    private void loadTransactionData() {
//        RetrofitConfig retrofitConfig = new RetrofitConfig();
//        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);
//
//        transactionApi.getAllTransactions()
//                .enqueue(new Callback<List<Transaction>>() {
//                    @Override
//                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
//                        transactionList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                        populateListView(response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        tvTotalBalance1.setText(t.getMessage());
//                    }
//                });
//    }

//    private void populateListView(List<Transaction> transactions) {
//        List<Transaction> transactionSaved = new ArrayList<>();
//
//        for (Transaction t: transactions) {
//            transactionSaved.add(t);
//        }
//        TransactionAdapter transactionAdapter = new TransactionAdapter(transactionSaved);
//        transactionList.setAdapter(transactionAdapter);
//    }
//
//    private void initializeElement() {
////        tvTotalBalance1 = findViewById(R.id.tvTotalBalance1);
//        transactionList = findViewById(R.id.transactionList);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}