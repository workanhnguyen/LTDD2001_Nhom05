package com.example.backend.repositories;

import com.example.backend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE wallet.id = ?1")
    List<Transaction> findByWalletId (Integer walletId);
    @Query("SELECT t FROM Transaction t WHERE categoryType.id = ?1")
    List<Transaction> findByCategoryTypeId (Integer categoryTypeId);
    @Query("SELECT t FROM Transaction t WHERE categoryType.name LIKE %?1%")
    List<Transaction> findByKeyword(String keyword);
}
