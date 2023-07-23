package com.example.quanlychitieu.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.quanlychitieu.adapters.TransactionAdapter;
import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.utils.CustomConstant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverViewFragment extends Fragment {
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    String resultData;
    TextView tvTotalBalance1, filterTitle;
    RecyclerView transactionList;
    LinearLayout linearLayoutFilter;
    ImageView switchShowHideBalance;
    boolean isBalanceShowed = true;
    public OverViewFragment() { }
    public static OverViewFragment newInstance(Bundle bundle) {
        OverViewFragment fragment = new OverViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Show the action bar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setTitle("Chào Anh Nguyễn!");
            activity.getSupportActionBar().setElevation(0);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_over_view, container, false);
        return view;
    }
    // Resolve UI here
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeElement(view);
//        loadTransactionData();
        handleSwitchToStatisticFilter();
        handleSwitchShowHideBalance();
    }
    private void handleSwitchShowHideBalance() {
        switchShowHideBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBalanceShowed = !isBalanceShowed;

                if (isBalanceShowed)
                    switchShowHideBalance.setImageResource(R.drawable.baseline_visibility_24);
                else
                    switchShowHideBalance.setImageResource(R.drawable.baseline_visibility_off_24);
            }
        });
    }
    private void handleSwitchToStatisticFilter() {
        linearLayoutFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StatisticFilterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
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
        linearLayoutFilter = view.findViewById(R.id.linearLayoutFilter);
        switchShowHideBalance = view.findViewById(R.id.switchShowHideBalance);

        filterTitle = view.findViewById(R.id.filterTitle);
        filterTitle.setText(getString(R.string.this_month));
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_renew, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.btnRenew) {
            // handle refresh data here
            Toast.makeText(getActivity(), "Refresh Data", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method to get data from another activity and back to origin activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK && data != null) {
                String title = data.getStringExtra("sts_filter");
                // Handle filter transaction here
                if (title.equals(CustomConstant.FILTER_STATISTIC_LAST_MONTH))
                    filterTitle.setText(getString(R.string.last_month));
                else if (title.equals(CustomConstant.FILTER_STATISTIC_THIS_MONTH))
                    filterTitle.setText(getString(R.string.this_month));
                else if (title.equals(CustomConstant.FILTER_STATISTIC_OTHER_MONTH))
                    filterTitle.setText(getString(R.string.another_month));
            } else {
                // Handle the case where the user canceled or there was an error in the second activity
            }
        }
    }
}