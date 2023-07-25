package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.WalletApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.views.WalletView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletPresenter {
    private final WalletView walletView;

    public WalletPresenter(WalletView walletView) {
        this.walletView = walletView;
    }

    public void loadWalletByUserId(Integer userId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.getWalletsByUserId(userId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Wallet>> call, Response<List<Wallet>> response) {
                walletView.showWalletList(response.body());
            }

            @Override
            public void onFailure(Call<List<Wallet>> call, Throwable t) {
                walletView.showGetDataError();
            }
        });
    }

    public void loadSumOfBalance(Integer userId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.getSumOfBalanceByUserId(userId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                walletView.showSumOfBalance(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }
}
