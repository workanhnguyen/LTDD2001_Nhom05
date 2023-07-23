package com.example.backend.daos;

import com.example.backend.dtos.TransactionDto;
import com.example.backend.models.Transaction;
import com.example.backend.repositories.TransactionRepository;
import com.example.backend.models.*;
import com.example.backend.repositories.CategoryTypeRepository;
import com.example.backend.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransaction(int id){
        return transactionRepository.findById(id);
    }
    public List<Transaction> getAllTransactionsByUserId(Integer id) {
        return transactionRepository.findByUserId(id);
    }
    public Long getSumOfExpenseByUserId(Integer userId, String type) {
        return transactionRepository.sumOfCategoryType(userId, type);
    }
    public List<Transaction> getAllTransactionByWalletId(int id) {
        return transactionRepository.findByWalletId(id);
    }

    public List<Transaction> getAllTransactionByCateTypeId(int id) {
        return transactionRepository.findByCategoryTypeId(id);
    }

    public TransactionDto addNewTransaction(TransactionDto transactionDto) throws Exception {
        Wallet wallet = walletRepository.findById(transactionDto.getWalletId())
                .orElseThrow(Exception::new);
        CategoryType categoryType = categoryTypeRepository.findById(transactionDto.getCategoryTypeId())
                .orElseThrow(Exception::new);

        Transaction transaction = new Transaction();
        transaction.setDescription(transactionDto.getDescription());
        transaction.setCreatedDate(transactionDto.getCreatedDate());
        transaction.setTotal(transactionDto.getTotal());
        transaction.setImage(transactionDto.getImage());
        transaction.setWallet((wallet));
        transaction.setCategoryType(categoryType);

        return modelMapper.map(transactionRepository.save(transaction), TransactionDto.class);
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

    public TransactionDto patchTransaction(int id, TransactionDto transactionDto) throws Exception {
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
        }

        if (transactionDto.getCategoryTypeId() != null) {
            CategoryType categoryType = categoryTypeRepository.findById(transactionDto.getCategoryTypeId())
                    .orElseThrow(Exception::new);
        }

        Transaction updateTransaction = transactionRepository.save(transaction);

        return modelMapper.map(updateTransaction, TransactionDto.class);
    }

    public boolean deleteTransaction(int id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        } else return false;
    }
    public List<Transaction> getTransactionByKeyword (@RequestParam("kw") String keyword) {
        return transactionRepository.findByKeyword(keyword);
    }
}
