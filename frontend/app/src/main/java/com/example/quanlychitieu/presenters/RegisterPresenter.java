package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.UserApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.views.RegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private final RegisterView registerView;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void register(UserDto userDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();

        UserApi userApi = retrofitConfig.getRetrofit().create(UserApi.class);
        userApi.register(userDto).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                registerView.saveRegisterInfo(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                registerView.showRegisterError();
            }
        });
    }
}
