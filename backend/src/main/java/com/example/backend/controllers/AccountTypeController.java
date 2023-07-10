package com.example.backend.controllers;

import com.example.backend.daos.AccountTypeDao;
import com.example.backend.dto.AccountTypeDto;
import com.example.backend.models.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account-types")
@CrossOrigin
public class AccountTypeController {
    @Autowired
    private AccountTypeDao accountTypeDao;

    @GetMapping
    public ResponseEntity<List<AccountTypeDto>> getAllAccountTypes() {
        return ResponseEntity.ok().body(accountTypeDao.getAllAccountTypes());
    }

    @PostMapping
    public ResponseEntity<AccountTypeDto> addNewAccountType(@RequestBody AccountTypeDto accountTypeDto) throws Exception {
        return new ResponseEntity<>(accountTypeDao.addNewAccountType(accountTypeDto), HttpStatus.CREATED);
    }
}
