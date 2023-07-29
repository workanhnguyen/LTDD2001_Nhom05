package com.example.quanlychitieu.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.CreateWalletActivity;
import com.example.quanlychitieu.adapters.WalletAdapter;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.presenters.WalletPresenter;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.views.WalletView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class WalletFragment extends Fragment implements WalletView {
    RecyclerView walletList;
    TextView txtShowSumBalance, walletAlertLoadingData;
    WalletAdapter adapter;
    FloatingActionButton btnCreateNewWallet;
    SharedPreferences sharedPreferences;
    WalletPresenter walletPresenter;
    public WalletFragment() { }

    public static WalletFragment newInstance(Bundle bundle) {
        WalletFragment fragment = new WalletFragment();
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
            activity.getSupportActionBar().setTitle(getString(R.string.account));
            activity.getSupportActionBar().setElevation(0);
        }

        walletPresenter = new WalletPresenter(this);
        sharedPreferences = requireActivity().getSharedPreferences("loggingUser", Context.MODE_PRIVATE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeElement(view);
        loadData();
        handleSwitchToCreateWalletActivity();
    }

    private void loadData() {
        walletAlertLoadingData.setText(getString(R.string.loading_data));

        walletPresenter.loadWalletByUserId(sharedPreferences.getInt("id", 1));
        walletPresenter.loadSumOfBalance(sharedPreferences.getInt("id", 1));
    }

    private void handleSwitchToCreateWalletActivity() {
        btnCreateNewWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateWalletActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populateListView(List<Wallet> list) {
        adapter = new WalletAdapter(list);
        adapter.setContext(getActivity());
        walletList.setLayoutManager(new LinearLayoutManager(getActivity()));
        walletList.setAdapter(adapter);
    }

    private void initializeElement(View view) {
        txtShowSumBalance = view.findViewById(R.id.sumBalance);
        walletAlertLoadingData = view.findViewById(R.id.walletAlertLoadingData);

        btnCreateNewWallet = view.findViewById(R.id.btnCreateNewWallet);
        walletList = view.findViewById(R.id.walletList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void showWalletList(List<Wallet> list) {
        if (!list.isEmpty()) {
            walletAlertLoadingData.setText("");
            populateListView(list);
        } else {
            walletAlertLoadingData.setText(getString(R.string.no_data));
        }
    }

    @Override
    public void showSumOfBalance(Long sumBalance) {
        txtShowSumBalance.setText(CommonUtil.getMoneyFormat(sumBalance));
    }

    @Override
    public void showGetDataError() {
        walletAlertLoadingData.setText(getString(R.string.error_loading_data));
        txtShowSumBalance.setText("0 đ");
    }
}
