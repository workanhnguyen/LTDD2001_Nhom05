package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.UserApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.views.AdminView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPresenter {
    private final AdminView adminView;

    public AdminPresenter(AdminView adminView) {
        this.adminView = adminView;
    }

    public void getUsers() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        UserApi userApi = retrofitConfig.getRetrofit().create(UserApi.class);

        userApi.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                adminView.showUserList(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                adminView.showError();
            }
        });
    }
}
