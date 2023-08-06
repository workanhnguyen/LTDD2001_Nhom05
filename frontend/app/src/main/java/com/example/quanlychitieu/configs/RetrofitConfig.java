package com.example.quanlychitieu.configs;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private Retrofit retrofit;

    public RetrofitConfig() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor);

        // Remember to change your local IP
        retrofit = new Retrofit.Builder()
                .baseUrl("https://ltdd-nhom-05-git-mobilegroup5-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okBuilder.build())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
