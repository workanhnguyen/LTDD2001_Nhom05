package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @POST("/users/login")
    Call<User> login(@Body User requestUser);
    @POST("/users")
    Call<User> register(@Body UserDto userDto);
    @PATCH("users/{userId}")
    Call<User> updateUser(@Path("userId") Integer userId, @Body UserDto userDto);
}
