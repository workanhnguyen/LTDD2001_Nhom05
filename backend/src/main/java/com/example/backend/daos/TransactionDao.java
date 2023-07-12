package com.example.backend.daos;

import com.example.backend.dtos.TransactionDto;
import com.example.backend.models.Transaction;
import com.example.backend.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TransactionDao {
    @Autowired
    private TransactionRepository transactionRepository;

    private ModelMapper modelMapper;

    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map((t) -> modelMapper.map(t, TransactionDto.class)).collect(Collectors.toList());
    }
}
