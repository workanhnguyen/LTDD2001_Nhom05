package com.example.backend.repositories;

import com.example.backend.models.User;
import com.example.backend.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    @Query("SELECT w FROM Wallet w WHERE user.id = ?1")
    List<Wallet> findByUserId (Integer userId);
    @Query("SELECT SUM(w.balance) FROM Wallet w WHERE user.id = ?1")
    Long sumOfBalanceByUserId(Integer userId);
}
