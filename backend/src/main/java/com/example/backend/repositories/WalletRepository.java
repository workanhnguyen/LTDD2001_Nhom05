package com.example.backend.repositories;

import com.example.backend.models.User;
import com.example.backend.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
//    List<Wallet> findByNameContaining(String name);
    List<Wallet> findByUserId (User user);

}
