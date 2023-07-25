package com.example.backend.controllers;

import com.example.backend.daos.WalletDao;
import com.example.backend.dtos.WalletDto;
import com.example.backend.models.Wallet;
import com.example.backend.repositories.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    private WalletDao walletDao;

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        return ResponseEntity.ok().body(walletDao.getAllWallets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Wallet>> getWallet(@PathVariable int id) {
        return ResponseEntity.ok().body(walletDao.getWallet(id));
    }

    @GetMapping(value = "/by-user")
    public ResponseEntity<List<Wallet>> getWalletsByUserId(@RequestParam(name = "userId") int id) throws Exception {
        return ResponseEntity.ok().body(walletDao.getWalletsByUserId(id));
    }

    @GetMapping(value = "/sum-balance/by-user", params = "userId")
    public ResponseEntity<Long> sumBalanceByUserId(@RequestParam(name = "userId") Integer userId) {
        return ResponseEntity.ok().body(walletDao.sumBalanceByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<WalletDto> addNewWallet(@RequestBody WalletDto walletDto) throws Exception {
        return new ResponseEntity<>(walletDao.addNewWallet(walletDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<WalletDto> updateWallet (@PathVariable int id, @RequestBody WalletDto walletDto) throws Exception {
        return ResponseEntity.ok().body(walletDao.updateWallet(id, walletDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WalletDto> patchWallet (@PathVariable int id, @RequestBody WalletDto walletDto) throws Exception {
        return ResponseEntity.ok().body(walletDao.patchWallet(id, walletDto));
    }

    @DeleteMapping("/{id}")
    public boolean DeleteWallet(@PathVariable int id) {
        return walletDao.deleteWallet(id);
    }
}

