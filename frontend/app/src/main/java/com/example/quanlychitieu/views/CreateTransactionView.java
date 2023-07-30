package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Transaction;

public interface CreateTransactionView {
    void showAddedTransaction(Transaction transaction);
    void showAddingTransactionError();
}
