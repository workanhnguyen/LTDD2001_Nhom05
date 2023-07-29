package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.apis.WalletApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.TransactionDto;
import com.example.quanlychitieu.dtos.WalletDto;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.views.EditWalletView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditWalletPresenter {
    private final EditWalletView editWalletView;

    public EditWalletPresenter(EditWalletView editWalletView) {
        this.editWalletView = editWalletView;
    }

    public void updateWallet(Integer walletId, WalletDto walletDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.updateWallet(walletId, walletDto).enqueue(new Callback<Wallet>() {
            @Override
            public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                editWalletView.showUpdatedWallet(response.body());
            }

            @Override
            public void onFailure(Call<Wallet> call, Throwable t) {
                editWalletView.showUpdateWalletError();
            }
        });
    }

    public void deleteWallet(Integer walletId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.deleteWallet(walletId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                editWalletView.showDeleteWalletResult(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void addNewTransactionByUpdateWallet(TransactionDto transactionDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.addNewTransaction(transactionDto).enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                editWalletView.showAddNewTransactionByUpdateWallet(response.body());
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {

            }
        });
    }
}
