package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.models.AccountType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountTypeApi {
    @GET("/account-types")
    Call<List<AccountType>> getAllAccountType();

    @POST("/account-types")
    Call<AccountType> addNewAccountType(@Body AccountType accountType);
}
