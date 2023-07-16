package com.example.backend.controllers;

import com.example.backend.daos.WalletDao;
import com.example.backend.dtos.AccountTypeDto;
import com.example.backend.dtos.WalletDto;
import com.example.backend.models.Wallet;
import com.example.backend.repositories.WalletRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    //Test this code to more fun
    public static Logger logger = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    private WalletDao walletDao;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<WalletDto>> getAllWallets() {
        return ResponseEntity.ok().body(walletDao.getAllWallets());
    }
//    @RequestMapping(value = "/wallets/", method = RequestMethod.GET)
//    public ResponseEntity<List<Wallet>> getAllWallet1() {
//        List<Wallet> listWallet = walletRepository.findAll();
//        if (listWallet.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<List<Wallet>>(listWallet, HttpStatus.OK);
//    }


//    @RequestMapping(value = "/wallets/", method = RequestMethod.GET)
//    public ResponseEntity<List<Wallet>> getAllWallet(HttpServletResponse response) {
//        List<Wallet> listWallet = walletRepository.findAll();
//        if (listWallet.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        response.setStatus(HttpStatus.OK.value());
//        return new ResponseEntity<List<Wallet>>(listWallet, HttpStatus.OK);
//    }
//

//    @GetMapping
//    public ResponseEntity<List<WalletDto>> getAllWallet() {
//        return ResponseEntity.ok().body(walletDao.getAllWallets());
//    }

//    @RequestMapping(value = "/wallets/{id}", method = RequestMethod.GET)
//    public Wallet findWalletById(@PathVariable("id") int id){
//        Wallet wallet = walletRepository.findById(id);
//        if (wallet == null){
//            ResponseEntity.notFound().build();
//        }
//        return wallet;
//    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<WalletDto> getWallet(@PathVariable int id) {
        return ResponseEntity.ok().body(walletDao.getWallet(id));
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<WalletDto>> getWalletByUserId(@RequestParam("userId") int id) throws Exception {
        return ResponseEntity.ok().body((List<WalletDto>) walletDao.getWalletByUserId(id));
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

