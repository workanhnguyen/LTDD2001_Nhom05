package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Transaction;

import java.util.List;

public interface OverViewView {
    void showTransactionList(List<Transaction> list);
    void showTransactionError();
}
