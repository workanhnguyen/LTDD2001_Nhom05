package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Transaction;

import java.util.List;

public interface OverViewView {
    void showTransactionList(List<Transaction> list);
    void showGetDataError();
    void showTotalBalance(Long sumOfBalance);
    void showSumOfExpense(Long sumOfExpense);
    void showSumOfIncome(Long sumOfIncome);
}
