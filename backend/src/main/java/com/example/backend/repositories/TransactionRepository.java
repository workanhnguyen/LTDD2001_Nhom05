package com.example.backend.repositories;

import com.example.backend.models.CategoryType;
import com.example.backend.models.Transaction;
import com.example.backend.models.User;
import com.example.backend.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.wallet.id=?1")
    List<Transaction> findByWalletId (Integer walletId);
    List<Transaction> findByCategoryTypeId (Integer categoryId);

    // Statistic - ALL
    @Query("SELECT t FROM Transaction t, Wallet w WHERE w.user.id=?1 AND t.wallet.id=w.id ORDER BY t.createdDate DESC")
    List<Transaction> findAllTransactionByUserId(Integer userId);
    @Query("SELECT COALESCE(SUM(t.total),0) AS sum FROM Transaction t, Wallet w WHERE w.user.id=?1 AND t.wallet.id=w.id AND (t.categoryType.categoryRoot.type LIKE 'EXPENSE')")
    Long sumAllExpenseByUserId(Integer userId);
    @Query("SELECT COALESCE(SUM(t.total),0) AS sum FROM Transaction t, Wallet w WHERE w.user.id=?1 AND t.wallet.id=w.id AND (t.categoryType.categoryRoot.type LIKE 'INCOME')")
    Long sumAllIncomeByUserId(Integer userId);

    // Statistic - MONTH
    @Query("SELECT t FROM Transaction t, Wallet w WHERE w.user.id=?1 AND t.wallet.id=w.id AND t.wallet.id=w.id AND ?2 <= t.createdDate AND t.createdDate <= ?3")
    List<Transaction> findMonthTransactionByUserId(Integer userId, Long secondsStartTime, Long secondsEndTime);
    @Query("SELECT COALESCE(SUM(t.total),0) AS sum FROM Transaction t, Wallet w WHERE w.user.id=?1 AND t.wallet.id=w.id AND (t.categoryType.categoryRoot.type LIKE 'EXPENSE') AND ?2 <= t.createdDate AND t.createdDate <= ?3")
    Long sumMonthExpenseByUserId(Integer userId, Long secondsStartTime, Long secondsEndTime);
    @Query("SELECT COALESCE(SUM(t.total),0) AS sum FROM Transaction t, Wallet w WHERE w.user.id=?1 AND t.wallet.id=w.id AND (t.categoryType.categoryRoot.type LIKE 'INCOME') AND ?2 <= t.createdDate AND t.createdDate <= ?3")
    Long sumMonthIncomeByUserId(Integer userId, Long secondsStartTime, Long secondsEndTime);
}
