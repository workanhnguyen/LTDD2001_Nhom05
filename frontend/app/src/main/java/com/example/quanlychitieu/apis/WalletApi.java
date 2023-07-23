package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.models.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WalletApi {
    @GET("/wallets/sum-balance?userId=2")
    Call<Long> getSumOfBalanceByUserId();
}
