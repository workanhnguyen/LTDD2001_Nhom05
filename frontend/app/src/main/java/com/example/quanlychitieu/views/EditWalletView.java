package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;

public interface EditWalletView {
    void showUpdatedWallet(Wallet updatedWallet);
    void showAddNewTransactionByUpdateWallet(Transaction transaction);
    void showDeleteWalletResult(Boolean result);
    void showUpdateWalletError();
}
