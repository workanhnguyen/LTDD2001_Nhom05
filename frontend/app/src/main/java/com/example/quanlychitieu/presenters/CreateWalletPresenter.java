package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.apis.WalletApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.TransactionDto;
import com.example.quanlychitieu.dtos.WalletDto;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.views.CreateWalletView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateWalletPresenter {
    private final CreateWalletView createWalletView;

    public CreateWalletPresenter(CreateWalletView createWalletView) {
        this.createWalletView = createWalletView;
    }

    public void addNewWallet(WalletDto walletDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.addNewWallet(walletDto).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                createWalletView.showAddedWallet(response.body());
            }

            @Override
            public void onFailure(Call<Wallet> call, Throwable t) {
                createWalletView.showAddingWalletError();
            }
        });
    }
}
