package com.example.backend.controllers;

import com.example.backend.daos.AccountRootDao;
import com.example.backend.daos.AccountTypeDao;
import com.example.backend.dtos.AccountRootDto;
import com.example.backend.dtos.AccountTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account-roots")
@CrossOrigin
public class AccountRootController {
    @Autowired
    private AccountRootDao accountRootDao;

    @GetMapping
    public ResponseEntity<List<AccountRootDto>> getAllAccountRoots() {
        return ResponseEntity.ok().body(accountRootDao.getAllAccountRoots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountRootDto> getAccountRoot(@PathVariable int id) {
        return ResponseEntity.ok().body(accountRootDao.getAccountRoot(id));
    }

    @PostMapping
    public ResponseEntity<AccountRootDto> addNewAccountRoot(@RequestBody AccountRootDto accountRootDto) throws Exception {
        return new ResponseEntity<>(accountRootDao.addNewAccountRoot(accountRootDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountRootDto> updateAccountRoot (@PathVariable int id, @RequestBody AccountRootDto accountRootDto) throws Exception {
        return ResponseEntity.ok().body(accountRootDao.updateAccountRoot(id, accountRootDto));
    }

    @DeleteMapping("/{id}")
    public boolean deleteAccountRoot(@PathVariable int id) {
        return accountRootDao.deleteAccountRoot(id);
    }


}


