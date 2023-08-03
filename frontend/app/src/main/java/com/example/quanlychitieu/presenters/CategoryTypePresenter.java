package com.example.quanlychitieu.presenters;

import com.example.quanlychitieu.apis.CategoryTypeApi;
import com.example.quanlychitieu.configs.RetrofitConfig;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.views.CategoryTypeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryTypePresenter {
    private final CategoryTypeView categoryTypeView;

    public CategoryTypePresenter(CategoryTypeView categoryTypeView) {
        this.categoryTypeView = categoryTypeView;
    }

    public void loadExpenseCategories() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        CategoryTypeApi categoryTypeApi = retrofitConfig.getRetrofit().create(CategoryTypeApi.class);

        categoryTypeApi.getExpenseCategories().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<CategoryType>> call, Response<List<CategoryType>> response) {
                categoryTypeView.showExpenseCategories(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryType>> call, Throwable t) {
                categoryTypeView.showError();
            }
        });
    }

    public void loadIncomeCategories() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        CategoryTypeApi categoryTypeApi = retrofitConfig.getRetrofit().create(CategoryTypeApi.class);

        categoryTypeApi.getIncomeCategories().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<CategoryType>> call, Response<List<CategoryType>> response) {
                categoryTypeView.showIncomeCategories(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryType>> call, Throwable t) {
                categoryTypeView.showError();
            }
        });
    }
}
