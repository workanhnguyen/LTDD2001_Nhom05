package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.WalletApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.views.ChooseWalletView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseWalletPresenter {
    private final ChooseWalletView chooseWalletView;

    public ChooseWalletPresenter(ChooseWalletView chooseWalletView) {
        this.chooseWalletView = chooseWalletView;
    }

    public void loadWalletsData(Integer userId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.getWalletsByUserId(userId).enqueue(new Callback<List<Wallet>>() {
            @Override
            public void onResponse(Call<List<Wallet>> call, Response<List<Wallet>> response) {
                chooseWalletView.showWalletList(response.body());
            }

            @Override
            public void onFailure(Call<List<Wallet>> call, Throwable t) {
                chooseWalletView.showWalletError();
            }
        });
    }
}
