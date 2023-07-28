package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.UserApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.dtos.UserDto;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.views.EditUserInfoView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserInfoPresenter {
    private final EditUserInfoView editUserInfoView;

    public EditUserInfoPresenter(EditUserInfoView editUserInfoView) {
        this.editUserInfoView = editUserInfoView;
    }

    public void updateUser(Integer userId, UserDto userDto) {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        UserApi userApi = retrofitConfig.getRetrofit().create(UserApi.class);

        userApi.updateUser(userId, userDto).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                editUserInfoView.showUpdatedUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                editUserInfoView.showErrorUpdateUser();
            }
        });
    }
}
