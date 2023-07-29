package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;

public interface CreateWalletView {
    void showAddedWallet(Wallet wallet);
    void showAddedTransactionByCreateWallet(Transaction transaction);
    void showAddingWalletError();
}
