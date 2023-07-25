package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.models.Wallet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WalletApi {
    @GET("/wallets/sum-balance/by-user")
    Call<Long> getSumOfBalanceByUserId(@Query("userId") Integer userId);
    @GET("/wallets/by-user")
    Call<List<Wallet>> getWalletsByUserId(@Query("userId") Integer userId);
}
