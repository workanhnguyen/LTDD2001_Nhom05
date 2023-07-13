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

    @POST("/transactions")
    Call<Transaction> addNewTransaction(@Body Transaction transaction);
}
