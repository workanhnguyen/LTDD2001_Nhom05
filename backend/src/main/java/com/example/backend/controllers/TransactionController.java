package com.example.backend.controllers;

import com.example.backend.daos.TransactionDao;
import com.example.backend.daos.WalletDao;
import com.example.backend.dtos.TransactionDto;
import com.example.backend.dtos.WalletDto;
import com.example.backend.models.Transaction;
import com.example.backend.repositories.TransactionRepository;
import com.example.backend.repositories.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    //Test this code to more fun
    public static Logger logger = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private TransactionDao transactionDao;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransaction() {
        return ResponseEntity.ok().body(transactionDao.getAllTransaction());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable int id) {
        return ResponseEntity.ok().body(transactionDao.getTransaction(id));
    }

    @GetMapping(params = "walletId")
    public ResponseEntity<List<TransactionDto>> getTransactionByWalletId(@RequestParam("walletId") int id) throws Exception {
        return ResponseEntity.ok().body((List<TransactionDto>) transactionDao.getTransactionByWalletId(id));
    }

    @GetMapping(params = "categoryTypeId")
    public ResponseEntity<List<TransactionDto>> getTransactionByCategoryId(@RequestParam("categoryTypeId") int id) throws Exception {
        return ResponseEntity.ok().body((List<TransactionDto>) transactionDao.getTransactionByCateType(id));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> addNewTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        return new ResponseEntity<>(transactionDao.addNewTransaction(transactionDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction (@PathVariable int id, @RequestBody TransactionDto transactionDto) throws Exception {
        return ResponseEntity.ok().body(transactionDao.updateTransaction(id, transactionDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TransactionDto> patchTransaction (@PathVariable int id, @RequestBody TransactionDto transactionDto) throws Exception {
        return ResponseEntity.ok().body(transactionDao.patchTransaction(id, transactionDto));
    }

    @DeleteMapping("/{id}")
    public boolean deleteTransaction(@PathVariable int id) {
        return transactionDao.deleteTransaction(id);
    }

    @GetMapping("/search")
    public String getTransactionByKeyword(@RequestParam("keyword") String keyword, Model model) {
        List<Transaction> transactions = transactionRepository.findByKeyword(keyword);
        model.addAttribute("transactions", transactions);
        return "user/search";
    }
}
