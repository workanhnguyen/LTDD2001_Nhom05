package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.AccountTypeApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.AccountType;
import com.example.quanlychitieu.views.ChooseAccountTypeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseAccountTypePresenter {
    private final ChooseAccountTypeView chooseAccountTypeView;

    public ChooseAccountTypePresenter(ChooseAccountTypeView chooseAccountTypeView) {
        this.chooseAccountTypeView = chooseAccountTypeView;
    }

    public void loadAccountTypeData() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        AccountTypeApi accountTypeApi = retrofitConfig.getRetrofit().create(AccountTypeApi.class);

        accountTypeApi.getAllAccountType().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<AccountType>> call, Response<List<AccountType>> response) {
                chooseAccountTypeView.showAccountTypeList(response.body());
            }

            @Override
            public void onFailure(Call<List<AccountType>> call, Throwable t) {
                chooseAccountTypeView.showAccountTypeError();
            }
        });
    }
}
