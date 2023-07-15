package com.example.quanlychitieu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.StatisticFilterActivity;
import com.example.quanlychitieu.activities.UserSettingActivity;
import com.example.quanlychitieu.adapters.TransactionAdapter;
import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.Transaction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverViewFragment extends Fragment {
    TextView tvTotalBalance1;
    RecyclerView transactionList;
    LinearLayout linearLayoutFilter;
    public OverViewFragment() { }
    public static OverViewFragment newInstance(Bundle bundle) {
        OverViewFragment fragment = new OverViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Show the action bar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setElevation(0);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_over_view, container, false);
    }
    // Resolve UI here
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeElement(view);
        loadTransactionData();

        linearLayoutFilter = view.findViewById(R.id.linearLayoutFilter);

        handleGoToStatisticFilterActivity();
    }

    private void handleGoToStatisticFilterActivity() {
        linearLayoutFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StatisticFilterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadTransactionData() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.getAllTransactions()
                .enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        transactionList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        tvTotalBalance1.setText(t.getMessage());
                    }
                });
    }
    private void populateListView(List<Transaction> transactions) {
        List<Transaction> transactionSaved = new ArrayList<>();

        for (Transaction t: transactions) {
            transactionSaved.add(t);
        }
        TransactionAdapter transactionAdapter = new TransactionAdapter(transactionSaved);
        transactionList.setAdapter(transactionAdapter);
    }
    private void initializeElement(View view) {
        transactionList = view.findViewById(R.id.transactionList);
    }
}