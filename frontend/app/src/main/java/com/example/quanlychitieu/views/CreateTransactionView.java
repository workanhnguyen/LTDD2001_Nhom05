package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;

public interface CreateTransactionView {
    void showAddedTransaction(Transaction transaction);
    void showUpdatedWalletBalance(Wallet wallet);
    void showAddingTransactionError();
}
