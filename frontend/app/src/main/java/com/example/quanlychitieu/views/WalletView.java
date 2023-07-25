package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Wallet;

import java.util.List;

public interface WalletView {
    void showWalletList(List<Wallet> list);
    void showSumOfBalance(Long sumBalance);
    void showGetDataError();
}
