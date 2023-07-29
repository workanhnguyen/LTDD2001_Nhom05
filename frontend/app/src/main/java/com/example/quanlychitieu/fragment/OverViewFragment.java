package com.example.quanlychitieu.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.StatisticFilterActivity;
import com.example.quanlychitieu.adapters.TransactionAdapter;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.presenters.OverViewPresenter;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.views.OverViewView;

import java.util.List;

public class OverViewFragment extends Fragment implements OverViewView {
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    TextView loadDataAlert, filterTitle, tvTotalBalance, tvSumOfIncome, tvSumOfExpense;
    RecyclerView transactionList;
    LinearLayout linearLayoutFilter;
    ImageView switchShowHideBalance;
    boolean isBalanceShowed;
    private OverViewPresenter overViewPresenter;
    SharedPreferences sharedPreferences;
    SharedPreferences toggleShowBalance;
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

        overViewPresenter = new OverViewPresenter(this);
        sharedPreferences = requireActivity().getSharedPreferences("loggingUser", Context.MODE_PRIVATE);
        toggleShowBalance = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);

        // Show the action bar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setTitle(getString(R.string.hello) + " " + sharedPreferences.getString("lastName", "") + " " + sharedPreferences.getString("firstName", "") + "!");
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
        loadData();
        handleSwitchToStatisticFilter();

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
    private void loadData() {
        loadDataAlert.setText(getString(R.string.loading_data));
        overViewPresenter.loadTransactionsByUserId(sharedPreferences.getInt("id", 1));
        overViewPresenter.loadSumOfBalance(sharedPreferences.getInt("id", 1));
        overViewPresenter.loadSumOfExpense(sharedPreferences.getInt("id", 1));
        overViewPresenter.loadSumOfIncome(sharedPreferences.getInt("id", 1));
    }
    private void populateListView(List<Transaction> transactions) {
        TransactionAdapter adapter = new TransactionAdapter(transactions);
        adapter.setContext(getActivity());
        transactionList.setAdapter(adapter);
    }
    private void initializeElement(View view) {
        isBalanceShowed = toggleShowBalance.getBoolean("isShowBalance", true);

        tvTotalBalance = view.findViewById(R.id.tvTotalBalance);
        tvSumOfExpense = view.findViewById(R.id.sumOfExpense);
        tvSumOfIncome = view.findViewById(R.id.sumOfIncome);

        transactionList = view.findViewById(R.id.transactionList);
        linearLayoutFilter = view.findViewById(R.id.linearLayoutFilter);
        switchShowHideBalance = view.findViewById(R.id.switchShowHideBalance);
        loadDataAlert = view.findViewById(R.id.loadDataAlert);

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
            loadData();
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
                int year = data.getIntExtra("selected_year", -1);
                int month = data.getIntExtra("selected_month", -1);
                if (month >= 0 && month < 12 && year >= 0) {
                    title = String.format("%02d/%04d", month + 1, year);
                }
                filterTitle.setText(title);
                // Handle filter transaction here
                if (title.equals(CustomConstant.FILTER_STATISTIC_LAST_MONTH)) {
                    filterTitle.setText(getString(R.string.last_month));
                }
                else if (title.equals(CustomConstant.FILTER_STATISTIC_THIS_MONTH)) {
                    filterTitle.setText(getString(R.string.this_month));
                }
                else if (title.equals(CustomConstant.FILTER_STATISTIC_OTHER_MONTH)) {
                    filterTitle.setText(getString(R.string.another_month));
                }
            } else {
            }
        }
    }

    @Override
    public void showTransactionList(List<Transaction> list) {
        if (!list.isEmpty()) {
            loadDataAlert.setText("");
            transactionList.setLayoutManager(new LinearLayoutManager(getActivity()));
            populateListView(list);
        } else {
            loadDataAlert.setText(getString(R.string.no_data));
        }
    }

    @Override
    public void showGetDataError() {
        loadDataAlert.setText(getString(R.string.error_loading_data));
        tvTotalBalance.setText("0 đ");
        tvSumOfExpense.setText("0 đ");
        tvSumOfIncome.setText("0 đ");
    }

    @Override
    public void showTotalBalance(Long sumOfBalance) {
        tvTotalBalance.setText(CommonUtil.getMoneyFormat(sumOfBalance));
        if (isBalanceShowed) {
            switchShowHideBalance.setImageResource(R.drawable.baseline_visibility_24);
            tvTotalBalance.setText(CommonUtil.getMoneyFormat(sumOfBalance));
        }
        else {
            switchShowHideBalance.setImageResource(R.drawable.baseline_visibility_off_24);
            tvTotalBalance.setText("****** đ");
        }

        switchShowHideBalance.setOnClickListener(v -> {
            isBalanceShowed = !isBalanceShowed;

            if (isBalanceShowed) {
                switchShowHideBalance.setImageResource(R.drawable.baseline_visibility_24);
                tvTotalBalance.setText(CommonUtil.getMoneyFormat(sumOfBalance));
            }
            else {
                switchShowHideBalance.setImageResource(R.drawable.baseline_visibility_off_24);
                tvTotalBalance.setText("****** đ");
            }
        });
    }

    @Override
    public void showSumOfExpense(Long sumOfExpense) {
        tvSumOfExpense.setText(CommonUtil.getMoneyFormat(sumOfExpense));
    }

    @Override
    public void showSumOfIncome(Long sumOfIncome) {
        tvSumOfIncome.setText(CommonUtil.getMoneyFormat(sumOfIncome));
    }

}