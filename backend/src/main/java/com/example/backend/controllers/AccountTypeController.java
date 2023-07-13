package com.example.backend.controllers;

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
@RequestMapping("/account-types")
@CrossOrigin
public class AccountTypeController {
    @Autowired
    private AccountTypeDao accountTypeDao;

    @GetMapping
    public ResponseEntity<List<AccountTypeDto>> getAllAccountTypes() {
        return ResponseEntity.ok().body(accountTypeDao.getAllAccountTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTypeDto> getAccountType(@PathVariable int id) {
        return ResponseEntity.ok().body(accountTypeDao.getAccountType(id));
    }

    @PostMapping
    public ResponseEntity<AccountTypeDto> addNewAccountType(
            @RequestBody AccountTypeDto accountTypeDto) throws Exception {
        return new ResponseEntity<>(accountTypeDao.addNewAccountType(accountTypeDto),
                HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountTypeDto> updateAccountType (@PathVariable int id, @RequestBody AccountTypeDto accountTypeDto) throws Exception {
        return ResponseEntity.ok().body(accountTypeDao.updateAccountType(id, accountTypeDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountTypeDto> patchAccountType (@PathVariable int id, @RequestBody AccountTypeDto accountTypeDto) throws Exception {
        return ResponseEntity.ok().body(accountTypeDao.patchAccountType(id, accountTypeDto));
    }

    @DeleteMapping("/{id}")
    public boolean deleteAccountType(@PathVariable int id) {
        return accountTypeDao.deleteAccountType(id);
    }


}


