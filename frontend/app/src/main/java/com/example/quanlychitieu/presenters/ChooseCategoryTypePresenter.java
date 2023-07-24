package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.CategoryTypeApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.views.ChooseCategoryTypeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseCategoryTypePresenter {
    private final ChooseCategoryTypeView chooseCategoryTypeView;

    public ChooseCategoryTypePresenter(ChooseCategoryTypeView chooseCategoryTypeView) {
        this.chooseCategoryTypeView = chooseCategoryTypeView;
    }

    public void loadExpenseCategories() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        CategoryTypeApi categoryTypeApi = retrofitConfig.getRetrofit().create(CategoryTypeApi.class);

        categoryTypeApi.getExpenseCategories().enqueue(new Callback<List<CategoryType>>() {
            @Override
            public void onResponse(Call<List<CategoryType>> call, Response<List<CategoryType>> response) {
                chooseCategoryTypeView.showExpenseCategories(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryType>> call, Throwable t) {

            }
        });
    }

    public void loadIncomeCategories() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        CategoryTypeApi categoryTypeApi = retrofitConfig.getRetrofit().create(CategoryTypeApi.class);

        categoryTypeApi.getIncomeCategories().enqueue(new Callback<List<CategoryType>>() {
            @Override
            public void onResponse(Call<List<CategoryType>> call, Response<List<CategoryType>> response) {
                chooseCategoryTypeView.showIncomeCategories(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryType>> call, Throwable t) {

            }
        });
    }
}
