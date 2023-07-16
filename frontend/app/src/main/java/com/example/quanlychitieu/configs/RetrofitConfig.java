package com.example.quanlychitieu.configs;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private Retrofit retrofit;

    public RetrofitConfig() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {

        // Remember to change your local IP
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.20:8081")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
