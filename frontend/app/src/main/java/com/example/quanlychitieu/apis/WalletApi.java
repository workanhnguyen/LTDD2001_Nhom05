package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.dtos.WalletDto;
import com.example.quanlychitieu.models.Wallet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WalletApi {
    @GET("/wallets/sum-balance/by-user")
    Call<Long> getSumOfBalanceByUserId(@Query("userId") Integer userId);
    @GET("/wallets/by-user")
    Call<List<Wallet>> getWalletsByUserId(@Query("userId") Integer userId);
    @POST("/wallets")
    Call<Wallet> addNewWallet(@Body WalletDto walletDto);
    @PATCH("/wallets/{walletId}")
    Call<Wallet> updateWallet(@Path("walletId") Integer walletId, @Body WalletDto walletDto);
    @DELETE("/wallets/{walletId}")
    Call<Boolean> deleteWallet(@Path("walletId") Integer walletId);
}
