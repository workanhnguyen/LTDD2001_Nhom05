package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.UserApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.views.AdminEditUserView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminEditUserPresenter {
    private final AdminEditUserView adminEditUserView;

    public AdminEditUserPresenter(AdminEditUserView adminEditUserView) {
        this.adminEditUserView = adminEditUserView;
    }

    public void deleteUser(Integer userId) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        UserApi userApi = retrofitConfig.getRetrofit().create(UserApi.class);

        userApi.deleteUser(userId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                adminEditUserView.showDeleteUserResult(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                adminEditUserView.showDeleteError();
            }
        });
    }

    public void updateUser(Integer userId, UserDto userDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        UserApi userApi = retrofitConfig.getRetrofit().create(UserApi.class);

        userApi.updateUser(userId, userDto).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                adminEditUserView.showUpdatedUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                adminEditUserView.showUpdateError();
            }
        });
    }
}
