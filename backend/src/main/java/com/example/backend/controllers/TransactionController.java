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
@RequestMapping("/transactions")
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

    @GetMapping(value = "/by-wallet")
    public ResponseEntity<List<Transaction>> getTransactionByWalletId(@RequestParam(name = "walletId") int id) throws Exception {
        return ResponseEntity.ok().body((List<Transaction>) transactionDao.getTransactionByWalletId(id));
    }

    @GetMapping(value = "/by-category-type")
    public ResponseEntity<List<Transaction>> getTransactionByCategoryId(@RequestParam(name = "categoryTypeId") int id) throws Exception {
        return ResponseEntity.ok().body(transactionDao.getTransactionByCateType(id));
    }

    // Statistic - ALL
    @GetMapping(value = "/all/by-user")
    public ResponseEntity<List<Transaction>> getAllTransactionByUserId(@RequestParam(name = "userId") Integer userId) {
        return ResponseEntity.ok().body(transactionDao.getAllTransactionByUserId(userId));
    }
    @GetMapping(value = "/all/sum-expense/by-user")
    public ResponseEntity<Long> sumAllExpenseByUserId(@RequestParam(name = "userId") Integer userId) {
        return ResponseEntity.ok().body(transactionDao.sumAllExpenseByUserId(userId));
    }
    @GetMapping(value = "/all/sum-income/by-user")
    public ResponseEntity<Long> sumAllIncomeByUserId(@RequestParam(name = "userId") Integer userId) {
        return ResponseEntity.ok().body(transactionDao.sumAllIncomeByUserId(userId));
    }
//
    // Statistic - MONTH
    @GetMapping(value = "/month/by-user", params = {"userId", "secondsStartTime", "secondsEndTime"})
    public ResponseEntity<List<Transaction>> getMonthTransactionByUserId(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "secondsStartTime") Long secondsStartTime, @RequestParam(name = "secondsEndTime") Long secondsEndTime) {
        return ResponseEntity.ok().body(transactionDao.getMonthTransactionByUserId(userId, secondsStartTime, secondsEndTime));
    }
    @GetMapping(value = "/month/sum-expense/by-user", params = {"userId", "secondsStartTime", "secondsEndTime"})
    public ResponseEntity<Long> sumMonthExpenseByUserId(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "secondsStartTime") Long secondsStartTime, @RequestParam(name = "secondsEndTime") Long secondsEndTime) {
        return ResponseEntity.ok().body(transactionDao.sumMonthExpenseByUserId(userId, secondsStartTime, secondsEndTime));
    }
    @GetMapping(value = "/month/sum-income/by-user", params = {"userId", "secondsStartTime", "secondsEndTime"})
    public ResponseEntity<Long> sumMonthIncomeByUserId(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "secondsStartTime") Long secondsStartTime, @RequestParam(name = "secondsEndTime") Long secondsEndTime) {
        return ResponseEntity.ok().body(transactionDao.sumMonthIncomeByUserId(userId, secondsStartTime, secondsEndTime));
    }

    @PostMapping
    public Transaction addNewTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        return transactionDao.addNewTransaction(transactionDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction (@PathVariable int id, @RequestBody TransactionDto transactionDto) throws Exception {
        return ResponseEntity.ok().body(transactionDao.updateTransaction(id, transactionDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Transaction> patchTransaction (@PathVariable int id, @RequestBody TransactionDto transactionDto) throws Exception {
        return ResponseEntity.ok().body(transactionDao.patchTransaction(id, transactionDto));
    }

    @DeleteMapping("/{transactionId}")
    public boolean deleteTransaction(@PathVariable(name = "transactionId") Integer id) {
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
