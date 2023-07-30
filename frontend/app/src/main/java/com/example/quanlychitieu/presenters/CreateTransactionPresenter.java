package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.apis.WalletApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.TransactionDto;
import com.example.quanlychitieu.dtos.WalletDto;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.views.CreateTransactionView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTransactionPresenter {
    private final CreateTransactionView createTransactionView;

    public CreateTransactionPresenter(CreateTransactionView createTransactionView) {
        this.createTransactionView = createTransactionView;
    }

    public void addNewTransaction(TransactionDto transactionDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.addNewTransaction(transactionDto).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                createTransactionView.showAddedTransaction(response.body());
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                createTransactionView.showAddingTransactionError();
            }
        });
    }

    public void updateWalletBalance(Integer walletId, WalletDto walletDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.updateWallet(walletId, walletDto).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                createTransactionView.showUpdatedWalletBalance(response.body());
            }

            @Override
            public void onFailure(Call<Wallet> call, Throwable t) {
                createTransactionView.showAddingTransactionError();
            }
        });
    }
}
