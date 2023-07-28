package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.UserApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.views.ChangePasswordView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPresenter {
    private final ChangePasswordView changePasswordView;

    public ChangePasswordPresenter(ChangePasswordView changePasswordView) {
        this.changePasswordView = changePasswordView;
    }
    public void changePassword(Integer userId, UserDto userDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        UserApi userApi = retrofitConfig.getRetrofit().create(UserApi.class);

        userApi.changePassword(userId, userDto).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                changePasswordView.saveUpdatedUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                changePasswordView.showUpdatePasswordError();
            }
        });
    }
}
