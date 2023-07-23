package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.models.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TransactionApi {
    @GET("/transactions")
    Call<List<Transaction>> getAllTransactions();

    @GET("/transactions?userId=2")
    Call<List<Transaction>> getAllTransactionsByUserId();

    @GET("/transactions?userId=2&type=EXPENSE")
    Call<Long> getSumOfExpenseByUserId();

    @GET("/transactions?userId=2&type=INCOME")
    Call<Long> getSumOfIncomeByUserId();

    @POST("/transactions")
    Call<Transaction> addNewTransaction(@Body Transaction transaction);
}
