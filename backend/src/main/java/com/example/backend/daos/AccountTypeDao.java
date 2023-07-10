package com.example.backend.daos;

import com.example.backend.dto.AccountTypeDto;
import com.example.backend.models.AccountRoot;
import com.example.backend.models.AccountType;
import com.example.backend.repositories.AccountRootRepository;
import com.example.backend.repositories.AccountTypeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AccountTypeDao {
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private AccountRootRepository accountRootRepository;
    private ModelMapper modelMapper;

    public List<AccountTypeDto> getAllAccountTypes() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();

        return accountTypes.stream().map((a) -> modelMapper.map(a, AccountTypeDto.class))
                .collect(Collectors.toList());
    }

    public AccountTypeDto addNewAccountType(AccountTypeDto accountTypeDto) throws Exception {

        AccountRoot accountRoot = accountRootRepository.findById(accountTypeDto.getAccountRootId())
                .orElseThrow(Exception::new);

        AccountType accountType = new AccountType();
        accountType.setName(accountTypeDto.getName());
        accountType.setDescription(accountTypeDto.getDescription());
        accountType.setImage(accountTypeDto.getImage());
        accountType.setAccountRoot(accountRoot);

        return modelMapper.map(accountTypeRepository.save(accountType), AccountTypeDto.class);
    }
}
