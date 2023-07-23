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
    @Query("SELECT t FROM Transaction t, User u, Wallet w WHERE w.user.id=?1 AND t.wallet.id=w.id")
    List<Transaction> findByUserId (Integer userId);
//    @Query("SELECT SUM(t.total) FROM Transaction t INNER JOIN Wallet w ON t.wallet.id=w.id INNER JOIN User u ON w.user.id=u.id INNER JOIN CategoryType ct ON t.categoryType.id=ct.id INNER JOIN CategoryRoot cr ON ct.categoryRoot=cr.id WHERE u.id=?1 AND cr.type=?2")
//    Long sumOfCategoryType(Integer userId, String type);
    @Query("SELECT t FROM Transaction t WHERE categoryType.name LIKE %?1%")
    List<Transaction> findByKeyword(String keyword);
}
