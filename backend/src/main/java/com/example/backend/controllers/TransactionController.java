package com.example.backend.controllers;

import com.example.backend.daos.TransactionDao;
import com.example.backend.dtos.TransactionDto;
import com.example.backend.models.Transaction;
import com.example.backend.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        return ResponseEntity.ok().body(transactionDao.getAllTransaction());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> getTransaction(@PathVariable int id) {
        return ResponseEntity.ok().body(transactionDao.getTransaction(id));
    }

    @GetMapping(params = "walletId")
    public ResponseEntity<List<Transaction>> getTransactionByWalletId(@RequestParam("walletId") int id) throws Exception {
        return ResponseEntity.ok().body((List<Transaction>) transactionDao.getTransactionByWalletId(id));
    }

    @GetMapping(params = "categoryTypeId")
    public ResponseEntity<List<Transaction>> getTransactionByCategoryId(@RequestParam("categoryTypeId") int id) throws Exception {
        return ResponseEntity.ok().body((List<Transaction>) transactionDao.getTransactionByCateType(id));
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

    //Function not ready to use
//    @GetMapping
//    public List<TransactionDto> getTransactionsByKeyword(@RequestParam String keyword) {
//        List<Transaction> transactions = transactionDao.getTransactionByKeyword(keyword);
//        List<TransactionDto> transactionDTOS = new ArrayList<>();
//        for (Transaction transaction : transactions) {
//            transactionDTOS.add(new TransactionDto(transaction.getId(), transaction.getDescription(), transaction.getCreatedDate(),
//                    transaction.getTotal(), transaction.getImage(), transaction.getCategoryType().getId(), transaction.getWallet().getId()));
//        }
//        return transactionDTOS;
//    }
}
