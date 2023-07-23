package com.example.backend.repositories;

import com.example.backend.models.CategoryType;
import com.example.backend.models.Transaction;
import com.example.backend.models.User;
import com.example.backend.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByWalletId (Wallet wallet);
    List<Transaction> findByCategoryTypeId (CategoryType categoryType);
    // Not ready to use
//    List<Transaction> getByKeyword(String kw);
}
