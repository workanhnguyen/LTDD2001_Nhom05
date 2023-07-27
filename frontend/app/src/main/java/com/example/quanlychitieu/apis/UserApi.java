package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/users/login")
    Call<User> login(@Body User requestUser);

    @POST("/users")
    Call<User> register(@Body UserDto userDto);
}
