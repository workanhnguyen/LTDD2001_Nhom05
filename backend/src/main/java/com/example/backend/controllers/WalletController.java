package com.example.backend.controllers;

import com.example.backend.daos.WalletDao;
import com.example.backend.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletController {

    @Autowired
    private WalletDao walletDao;

    @GetMapping("/wallets")
    private List<Wallet> getAllWallets() {
        return walletDao.getAllWallets();
    }
}
