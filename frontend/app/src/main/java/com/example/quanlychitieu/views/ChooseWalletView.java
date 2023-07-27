package com.example.quanlychitieu.views;

import com.example.quanlychitieu.models.Wallet;

import java.util.List;

public interface ChooseWalletView {
    void showWalletList(List<Wallet> list);
    void showWalletError();
}
