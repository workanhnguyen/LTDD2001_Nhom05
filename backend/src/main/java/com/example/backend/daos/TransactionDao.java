package com.example.backend.daos;

import com.example.backend.dtos.TransactionDto;
import com.example.backend.models.*;
import com.example.backend.repositories.CategoryTypeRepository;
import com.example.backend.repositories.TransactionRepository;
import com.example.backend.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TransactionDao {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CategoryTypeRepository categoryTypeRepository;
    private ModelMapper modelMapper;

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
    public Optional<Transaction> getTransaction(int id){
        return transactionRepository.findById(id);
    }
    public List<Transaction> getTransactionByWalletId(int id) {
        return transactionRepository.findByWalletId(id);
    }
    public List<Transaction> getTransactionByCateType(int id) {
        return transactionRepository.findByCategoryTypeId(id);
    }

    // Statistic - ALL
    public List<Transaction> getAllTransactionByUserId(Integer userId) {
        List<Transaction> transactions = transactionRepository.findAllTransactionByUserId(userId);

        if (transactions == null || transactions.isEmpty())
            return new ArrayList<>();
        return transactionRepository.findAllTransactionByUserId(userId);
    }
    public Long sumAllExpenseByUserId(Integer userId) {
        return transactionRepository.sumAllExpenseByUserId(userId);
    }

    public Long sumAllIncomeByUserId(Integer userId) {
        return transactionRepository.sumAllIncomeByUserId(userId);
    }

    // Statistic - MONTH
    public List<Transaction> getMonthTransactionByUserId(Integer userId, Long secondsStartTime, Long secondsEndTime) {
        return transactionRepository.findMonthTransactionByUserId(userId, secondsStartTime, secondsEndTime);
    }
    public Long sumMonthExpenseByUserId(Integer userId, Long secondsStartTime, Long secondsEndTime) {
        return transactionRepository.sumMonthExpenseByUserId(userId, secondsStartTime, secondsEndTime);
    }
    public Long sumMonthIncomeByUserId(Integer userId, Long secondsStartTime, Long secondsEndTime) {
        return transactionRepository.sumMonthIncomeByUserId(userId, secondsStartTime, secondsEndTime);
    }

    public Transaction addNewTransaction(TransactionDto transactionDto) throws Exception {
        Wallet wallet = walletRepository.findById(transactionDto.getWalletId())
                .orElseThrow(Exception::new);
        CategoryType categoryType = categoryTypeRepository.findById(transactionDto.getCategoryTypeId())
                .orElseThrow(Exception::new);

        Transaction transaction = new Transaction();
        transaction.setDescription(transactionDto.getDescription());
        transaction.setCreatedDate(transactionDto.getCreatedDate());
        transaction.setTotal(transactionDto.getTotal());
        if (transactionDto.getImage() != null)
            transaction.setImage(transactionDto.getImage());
        transaction.setWallet((wallet));
        transaction.setCategoryType(categoryType);

        return transactionRepository.save(transaction);
    }

    public TransactionDto updateTransaction(int id, TransactionDto transactionDto) throws Exception {
        Wallet wallet = walletRepository.findById(transactionDto.getWalletId())
                .orElseThrow(Exception::new);
        CategoryType categoryType = categoryTypeRepository.findById(transactionDto.getCategoryTypeId())
                .orElseThrow(Exception::new);
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(Exception::new);

        transaction.setDescription(transactionDto.getDescription());
        transaction.setCreatedDate(transactionDto.getCreatedDate());
        transaction.setTotal(transactionDto.getTotal());
        transaction.setImage(transactionDto.getImage());
        transaction.setWallet((wallet));
        transaction.setCategoryType(categoryType);
        Wallet updatedWallet = walletRepository.save(wallet);

        return modelMapper.map(updatedWallet, TransactionDto.class);
    }

    public Transaction patchTransaction(int id, TransactionDto transactionDto) throws Exception {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(Exception::new);
        if (transactionDto.getImage() != null) {
            transaction.setImage((transactionDto.getImage()));
        }

        if (transactionDto.getDescription() != null) {
            transaction.setDescription(transactionDto.getDescription());
        }

        if (transactionDto.getCreatedDate() != null) {
            transaction.setCreatedDate(transactionDto.getCreatedDate());
        }

        if (transactionDto.getTotal() != null) {
            transaction.setTotal(transactionDto.getTotal());
        }

        if (transactionDto.getWalletId() != null) {
            Wallet wallet = walletRepository.findById(transactionDto.getWalletId())
                    .orElseThrow(Exception::new);
            transaction.setWallet(wallet);
        }

        if (transactionDto.getCategoryTypeId() != null) {
            CategoryType categoryType = categoryTypeRepository.findById(transactionDto.getCategoryTypeId())
                    .orElseThrow(Exception::new);
            transaction.setCategoryType(categoryType);
        }


        return transactionRepository.save(transaction);
    }

    public boolean deleteTransaction(Integer id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        } else return false;
    }

//    Function is not ready to use
//    public List<Transaction> getTransactionByKeyword(String keyword) {
//        return transactionRepository.getByKeyword(keyword);
//    }
}
