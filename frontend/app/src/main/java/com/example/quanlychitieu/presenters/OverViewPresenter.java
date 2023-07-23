package com.example.quanlychitieu.presenters;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.views.OverViewView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverViewPresenter {
    private OverViewView overViewView;
    List<Transaction> list;

    public OverViewPresenter(OverViewView overViewView) {
        this.overViewView = overViewView;
        list = new ArrayList<>();
    }

    public void loadTransactionList() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.getAllTransactions().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                list = response.body();
                overViewView.showTransactionList(list);
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                overViewView.showTransactionError();
            }
        });
    }
}
