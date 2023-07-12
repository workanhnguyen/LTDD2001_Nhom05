package com.example.backend.controllers;

import com.example.backend.daos.TransactionDao;
import com.example.backend.dtos.TransactionDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {
    @Autowired
    private TransactionDao transactionDao;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.ok().body(transactionDao.getAllTransactions());
    }
}
