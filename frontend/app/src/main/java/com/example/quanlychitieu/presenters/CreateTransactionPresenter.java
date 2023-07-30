package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.TransactionDto;
import com.example.quanlychitieu.models.Transaction;
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
}
