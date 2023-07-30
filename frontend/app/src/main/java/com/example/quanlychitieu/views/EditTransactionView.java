package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;

public interface EditTransactionView {
    void showUpdatedTransaction(Transaction transaction);
    void showDeleteTransactionResult(boolean isDeleted);
    void showUpdatedWalletBalance(Wallet wallet);
    void showErrorUpdatingTransaction();
    void showErrorDeletingTransaction();
}
