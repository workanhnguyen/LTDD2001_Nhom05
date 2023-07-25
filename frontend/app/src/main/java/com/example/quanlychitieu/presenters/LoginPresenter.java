package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.UserApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private final LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(User requestUser) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        UserApi userApi = retrofitConfig.getRetrofit().create(UserApi.class);

        userApi.login(requestUser).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loginView.showLoginInfo(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginView.showLoginError();
            }
        });
    }
}
