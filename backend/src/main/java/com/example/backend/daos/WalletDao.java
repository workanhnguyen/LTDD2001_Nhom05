package com.example.backend.daos;

import com.example.backend.models.Wallet;
import com.example.backend.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletDao {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAllWallets() {
        List<Wallet> wallets = new ArrayList<>();
        Streamable.of(walletRepository.findAll()).forEach(wallets::add);

        return wallets;
    }
}
