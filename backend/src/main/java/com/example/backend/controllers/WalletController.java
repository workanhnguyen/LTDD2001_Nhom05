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
    //Test this code to more fun
    public static Logger logger = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WalletDao walletDao;

    @RequestMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        return ResponseEntity.ok().body(walletDao.getAllWallets());
    }

    @GetMapping
    public Optional<Wallet> getWallet(@PathVariable int id) {
        return walletRepository.findById(id);
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<Wallet>> getWalletByUserId(@RequestParam("userId") int id) {
        return ResponseEntity.ok().body(walletDao.getWalletByUserId(id));
    }

    @GetMapping(value = "/sum-balance", params = "userId")
    public ResponseEntity<Long> getSumOfBalanceByUserId(@RequestParam(name = "userId") int userId) {
        return ResponseEntity.ok().body(walletDao.getSumOfBalanceByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WalletDto> addNewWallet(@RequestBody WalletDto walletDto) throws Exception {
        return new ResponseEntity<>(walletDao.addNewWallet(walletDto), HttpStatus.CREATED);
    }
    @RequestMapping(value ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<WalletDto> updateWallet (@PathVariable int id, @RequestBody WalletDto walletDto) throws Exception {
        return ResponseEntity.ok().body(walletDao.updateWallet(id, walletDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<WalletDto> patchWallet (@PathVariable int id, @RequestBody WalletDto walletDto) throws Exception {
        return ResponseEntity.ok().body(walletDao.patchWallet(id, walletDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean DeleteWallet(@PathVariable int id) {
        return walletDao.deleteWallet(id);
    }
}

