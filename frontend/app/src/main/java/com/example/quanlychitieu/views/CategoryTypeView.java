package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.CategoryType;

import java.util.List;

public interface CategoryTypeView {
    void showExpenseCategories(List<CategoryType> list);
    void showIncomeCategories(List<CategoryType> list);
    void showError();
}
