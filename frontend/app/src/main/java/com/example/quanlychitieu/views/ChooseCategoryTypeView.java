package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.CategoryType;

import java.util.List;

public interface ChooseCategoryTypeView {
    void showExpenseCategories(List<CategoryType> list);
    void showIncomeCategories(List<CategoryType> list);
    void showError();
}
