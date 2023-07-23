package com.example.backend.controllers;

import com.example.backend.daos.TransactionDao;
import com.example.backend.dtos.TransactionDto;
import com.example.backend.models.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {
    //Test this code to more fun
    public static Logger logger = LoggerFactory.getLogger(WalletController.class);
    @Autowired
    private TransactionDao transactionDao;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok().body(transactionDao.getAllTransactions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> getTransaction(@PathVariable int id) {
        return ResponseEntity.ok().body(transactionDao.getTransaction(id));
    }

    @GetMapping("/wallet")
    public ResponseEntity<List<Transaction>> getTransactionByWalletId(@RequestParam(name = "walletId") int id) throws Exception {
        return ResponseEntity.ok().body(transactionDao.getAllTransactionByWalletId(id));
    }

    @GetMapping("/category-type")
    public ResponseEntity<List<Transaction>> getTransactionByCategoryId(@RequestParam(name = "categoryTypeId") int id) throws Exception {
        return ResponseEntity.ok().body(transactionDao.getAllTransactionByCateTypeId(id));
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

//    @GetMapping("/search")
//    public String getTransactionByKeyword(@RequestParam("keyword") String keyword, Model model) {
//        List<Transaction> transactions = transactionDao.findByKeyword(keyword);
//        model.addAttribute("transactions", transactions);
//        return "transactions/search";
//    }
}
