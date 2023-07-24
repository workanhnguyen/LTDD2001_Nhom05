package com.example.quanlychitieu.apis;

import com.example.quanlychitieu.models.CategoryType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryTypeApi {
    @GET("/category-types/expense")
    Call<List<CategoryType>> getExpenseCategories();
    @GET("/category-types/income")
    Call<List<CategoryType>> getIncomeCategories();
}
