package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.TransactionApi;
import com.example.quanlychitieu.apis.WalletApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.views.OverViewView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverViewPresenter {
    private final OverViewView overViewView;

    public OverViewPresenter(OverViewView overViewView) {
        this.overViewView = overViewView;
    }

    public void loadAllTransactionsByUserId(Integer userId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.getAllTransactionsByUserId(userId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                overViewView.showTransactionList(response.body());
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                overViewView.showGetDataError();
            }
        });
    }
    public void loadSumOfBalanceByUserId(Integer userId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        WalletApi walletApi = retrofitConfig.getRetrofit().create(WalletApi.class);

        walletApi.getSumOfBalanceByUserId(userId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                overViewView.showTotalBalance(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }
    public void loadAllSumOfExpenseByUserId(Integer userId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.getAllSumOfExpenseByUserId(userId).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                overViewView.showSumOfExpense(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }

    public void loadSumOfIncomeByUserId(Integer userId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.getAllSumOfIncomeByUserId(userId).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                overViewView.showSumOfIncome(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }

    public void loadMonthTransactionsByUserId(Integer userId, Long secondsStartTime, Long secondsEndStartTime) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.getMonthTransactionsByUserId(userId, secondsStartTime, secondsEndStartTime).enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                overViewView.showTransactionList(response.body());
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                overViewView.showGetDataError();
            }
        });
    }

    public void loadMonthSumOfExpenseByUserId(Integer userId, Long secondsStartTime, Long secondsEndStartTime) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.getMonthSumOfExpenseByUserId(userId, secondsStartTime, secondsEndStartTime).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                overViewView.showSumOfExpense(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }

    public void loadMonthSumOfIncomeByUserId(Integer userId, Long secondsStartTime, Long secondsEndStartTime) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        TransactionApi transactionApi = retrofitConfig.getRetrofit().create(TransactionApi.class);

        transactionApi.getMonthSumOfIncomeByUserId(userId, secondsStartTime, secondsEndStartTime).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                overViewView.showSumOfIncome(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }
}
