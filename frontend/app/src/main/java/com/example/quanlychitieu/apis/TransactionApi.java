package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.dtos.TransactionDto;
import com.example.quanlychitieu.models.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TransactionApi {
    @GET("/transactions")
    Call<List<Transaction>> getAllTransactions();

    // Statistic - ALL
    @GET("/transactions/all/by-user")
    Call<List<Transaction>> getAllTransactionsByUserId(@Query("userId") Integer userId);
    @GET("/transactions/all/sum-expense/by-user")
    Call<Long> getSumOfExpenseByUserId(@Query("userId") Integer userId);
    @GET("/transactions/all/sum-income/by-user")
    Call<Long> getSumOfIncomeByUserId(@Query("userId") Integer userId);

    @POST("/transactions")
    Call<Transaction> addNewTransaction(@Body TransactionDto transactionDto);
    @PATCH("/transactions/{transactionId}")
    Call<Transaction> updateTransaction(@Path("transactionId") Integer transactionId, @Body TransactionDto transactionDto);
    @DELETE("/transactions/{transactionId}")
    Call<Boolean> deleteTransaction(@Path("transactionId") Integer transactionId);
}
