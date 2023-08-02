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
    Call<Long> getAllSumOfExpenseByUserId(@Query("userId") Integer userId);
    @GET("/transactions/all/sum-income/by-user")
    Call<Long> getAllSumOfIncomeByUserId(@Query("userId") Integer userId);

    // Statistic - MONTH
    @GET("/transactions/month/by-user")
    Call<List<Transaction>> getMonthTransactionsByUserId(@Query("userId") Integer userId, @Query("secondsStartTime") Long secondsStartTime, @Query("secondsEndTime") Long secondsEndTime);
    @GET("/transactions/month/sum-expense/by-user")
    Call<Long> getMonthSumOfExpenseByUserId(@Query("userId") Integer userId, @Query("secondsStartTime") Long secondsStartTime, @Query("secondsEndTime") Long secondsEndTime);
    @GET("/transactions/month/sum-income/by-user")
    Call<Long> getMonthSumOfIncomeByUserId(@Query("userId") Integer userId, @Query("secondsStartTime") Long secondsStartTime, @Query("secondsEndTime") Long secondsEndTime);

    @POST("/transactions")
    Call<Transaction> addNewTransaction(@Body TransactionDto transactionDto);
    @PATCH("/transactions/{transactionId}")
    Call<Transaction> updateTransaction(@Path("transactionId") Integer transactionId, @Body TransactionDto transactionDto);
    @DELETE("/transactions/{transactionId}")
    Call<Boolean> deleteTransaction(@Path("transactionId") Integer transactionId);
}
