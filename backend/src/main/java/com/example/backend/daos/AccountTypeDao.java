package com.example.backend.daos;

import com.example.backend.dtos.AccountTypeDto;
import com.example.backend.models.AccountRoot;
import com.example.backend.models.AccountType;
import com.example.backend.repositories.AccountRootRepository;
import com.example.backend.repositories.AccountTypeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public AccountTypeDto getAccountType(int id) {
        Optional<AccountType> accountType = accountTypeRepository.findById(id);
        return modelMapper.map(accountType, AccountTypeDto.class);
    }




    public AccountTypeDto addNewAccountType(AccountTypeDto accountTypeDto) throws Exception {

//        AccountType accountType = new AccountType();
//
//        accountType.setName(accountTypeDto.getName());
//        accountType.setImage(accountTypeDto.getImage());
//        accountType.setDescription(accountTypeDto.getDescription());
//
//        AccountRoot accountRoot = accountRootRepository.findById(accountTypeDto.getAccountRootId())
//                .orElseThrow(Exception::new);
//        accountType.setAccountRoot(accountRoot);
//
//        AccountType updatedAccountType = accountTypeRepository.save(accountType);

//        return modelMapper.map(updatedAccountType, AccountTypeDto.class);

        AccountRoot accountRoot = accountRootRepository.findById(accountTypeDto.getAccountRootId())
                .orElseThrow(Exception::new);

        AccountType accountType = new AccountType();
        accountType.setName(accountTypeDto.getName());
        accountType.setDescription(accountTypeDto.getDescription());
        accountType.setImage(accountTypeDto.getImage());
        accountType.setAccountRoot(accountRoot);

        return modelMapper.map(accountTypeRepository.save(accountType), AccountTypeDto.class);
    }

    public AccountTypeDto updateAccountType(int id, AccountTypeDto accountTypeDto) throws Exception {
        AccountType accountType = accountTypeRepository.findById(id)
                .orElseThrow(Exception::new);

        accountType.setName(accountTypeDto.getName());
        accountType.setImage(accountTypeDto.getImage());
        accountType.setDescription(accountTypeDto.getDescription());
        AccountRoot accountRoot = accountRootRepository.findById(accountTypeDto.getAccountRootId())
                .orElseThrow(Exception::new);
        accountType.setAccountRoot(accountRoot);
        AccountType updatedAccountType = accountTypeRepository.save(accountType);

        return modelMapper.map(updatedAccountType, AccountTypeDto.class);
    }

    public AccountTypeDto patchAccountType(int id, AccountTypeDto accountTypeDto) throws Exception {
        AccountType accountType = accountTypeRepository.findById(id)
                .orElseThrow(Exception::new);

        if (accountTypeDto.getName() != null) {
            accountType.setName(accountTypeDto.getName());
        }

        if (accountTypeDto.getImage() != null) {
            accountType.setImage(accountTypeDto.getImage());
        }

        if (accountTypeDto.getDescription() != null) {
            accountType.setDescription(accountTypeDto.getDescription());
        }

        if(accountTypeDto.getAccountRootId() != null) {
            AccountRoot accountRoot = accountRootRepository.findById(accountTypeDto.getAccountRootId())
                    .orElseThrow(Exception::new);
            accountType.setAccountRoot(accountRoot);
        }

        AccountType updatedAccountType = accountTypeRepository.save(accountType);

        return modelMapper.map(updatedAccountType, AccountTypeDto.class);
    }



    public boolean deleteAccountType(int id) {
        if (accountTypeRepository.existsById(id)) {
            accountTypeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public List<AccountTypeDto> getAccountTypeByAccountRoot(int id) throws Exception {
        AccountRoot accountRoot = accountRootRepository.findById(id)
                .orElseThrow(Exception::new);
        List<AccountType> accountTypes = accountTypeRepository.findByAccountRoot(accountRoot);

        return accountTypes.stream().map((a) -> modelMapper.map(a, AccountTypeDto.class))
                .collect(Collectors.toList());
    }

}


