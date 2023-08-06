package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {
    @GET("/users/{userId}")
    Call<User> getUserById(@Path("userId") Integer userId);
    @GET("/users")
    Call<List<User>> getUsers();
    @POST("/users/login")
    Call<User> login(@Body User requestUser);
    @POST("/users")
    Call<User> register(@Body UserDto userDto);
    @PATCH("users/{userId}")
    Call<User> updateUser(@Path("userId") Integer userId, @Body UserDto userDto);
    @PATCH("/users/password/{userId}")
    Call<User> changePassword(@Path("userId") Integer userId, @Body UserDto userDto);
    @DELETE("/users/{userId}")
    Call<Boolean> deleteUser(@Path("userId") Integer userId);
}
