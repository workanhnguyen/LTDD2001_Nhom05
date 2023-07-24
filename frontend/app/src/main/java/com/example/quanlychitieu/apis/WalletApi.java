package com.example.quanlychitieu.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WalletApi {
    @GET("/wallets/sum-balance/by-user")
    Call<Long> getSumOfBalanceByUserId(@Query("userId") Integer userId);
}
