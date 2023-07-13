package com.example.backend.daos;

import com.example.backend.dtos.AccountRootDto;

import com.example.backend.models.AccountRoot;

import com.example.backend.repositories.AccountRootRepository;
import com.example.backend.repositories.AccountTypeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AccountRootDao {
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private AccountRootRepository accountRootRepository;
    private ModelMapper modelMapper;

    public List<AccountRootDto> getAllAccountRoots() {
        List<AccountRoot> accountRoots = accountRootRepository.findAll();

        return accountRoots.stream().map((a) -> modelMapper.map(a, AccountRootDto.class))
                .collect(Collectors.toList());
    }

    public AccountRootDto getAccountRoot(int id) {
        Optional<AccountRoot> accountRoot = accountRootRepository.findById(id);
        return modelMapper.map(accountRoot, AccountRootDto.class);
    }

    public boolean deleteAccountRoot(int id) {
        if (accountRootRepository.existsById(id)) {
            accountRootRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }



    public AccountRootDto addNewAccountRoot(AccountRootDto accountRootDto) throws Exception {

        AccountRoot accountRoot = new AccountRoot();
        accountRoot.setName(accountRootDto.getName());
        accountRoot.setImage(accountRootDto.getImage());

        AccountRoot savedAccountRoot = accountRootRepository.save(accountRoot);

        return modelMapper.map(savedAccountRoot, AccountRootDto.class);
    }

    public AccountRootDto updateAccountRoot(int id, AccountRootDto updatedAccountRootDto) throws Exception {
        AccountRoot accountRoot = accountRootRepository.findById(id)
                .orElseThrow(Exception::new);

        accountRoot.setName(updatedAccountRootDto.getName());
        accountRoot.setImage(updatedAccountRootDto.getImage());

        AccountRoot updatedAccountRoot = accountRootRepository.save(accountRoot);

        return modelMapper.map(updatedAccountRoot, AccountRootDto.class);
    }

    public AccountRootDto patchAccountRoot(int id, AccountRootDto updatedAccountRootDto) throws Exception {
        AccountRoot accountRoot = accountRootRepository.findById(id)
                .orElseThrow(Exception::new);

        if (updatedAccountRootDto.getName() != null) {
            accountRoot.setName(updatedAccountRootDto.getName());
        }

        if (updatedAccountRootDto.getImage() != null) {
            accountRoot.setImage(updatedAccountRootDto.getImage());
        }

        AccountRoot updatedAccountRoot = accountRootRepository.save(accountRoot);

        return modelMapper.map(updatedAccountRoot, AccountRootDto.class);
    }


}


