package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.apis.WalletApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.TransactionDto;
import com.example.quanlychitieu.dtos.WalletDto;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.views.EditTransactionView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTransactionPresenter {
    private final EditTransactionView editTransactionView;

    public EditTransactionPresenter(EditTransactionView editTransactionView) {
        this.editTransactionView = editTransactionView;
    }

    public void updateTransaction(Integer transactionId, TransactionDto transactionDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.updateTransaction(transactionId, transactionDto).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                editTransactionView.showUpdatedTransaction(response.body());
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                editTransactionView.showErrorUpdatingTransaction();
            }
        });
    }

    public void deleteTransaction(Integer transactionId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.deleteTransaction(transactionId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                editTransactionView.showDeleteTransactionResult(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                editTransactionView.showErrorDeletingTransaction();
            }
        });
    }

    public void updateWalletBalance(Integer walletId, WalletDto walletDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.updateWallet(walletId, walletDto).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                editTransactionView.showUpdatedWalletBalance(response.body());
            }

            @Override
            public void onFailure(Call<Wallet> call, Throwable t) {
                editTransactionView.showErrorUpdatingTransaction();
            }
        });
    }
}
