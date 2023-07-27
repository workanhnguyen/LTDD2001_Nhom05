package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.AccountType;

import java.util.List;

public interface ChooseAccountTypeView {
    void showAccountTypeList(List<AccountType> list);
    void showAccountTypeError();
}
