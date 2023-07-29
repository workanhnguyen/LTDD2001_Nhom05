package com.example.backend.daos;

import com.example.backend.dtos.WalletDto;
import com.example.backend.models.AccountType;
import com.example.backend.models.User;
import com.example.backend.models.Wallet;
import com.example.backend.repositories.AccountTypeRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class WalletDao {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    private ModelMapper modelMapper;

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Optional<Wallet> getWallet(int id){
        return walletRepository.findById(id);
    }

    public List<Wallet> getWalletsByUserId(int id) {
        List<Wallet> wallets = walletRepository.findByUserId(id);

        if (wallets == null || wallets.isEmpty())
            return new ArrayList<>();
        return walletRepository.findByUserId(id);
    }

    public Long sumBalanceByUserId(Integer userId) {
        return walletRepository.sumBalanceByUserId(userId);
    }

    public Wallet addNewWallet(WalletDto walletDto) throws Exception {
        User user = userRepository.findById(walletDto.getUserId())
                .orElseThrow(Exception::new);
        AccountType accountType = accountTypeRepository.findById(walletDto.getAccountTypeId())
                .orElseThrow(Exception::new);

        Wallet wallet = new Wallet();
        wallet.setName(walletDto.getName());
        wallet.setDescription(walletDto.getDescription());
        wallet.setBalance(walletDto.getBalance());
        wallet.setUser((user));
        wallet.setAccountType(accountType);

        return walletRepository.save(wallet);
    }

    public WalletDto updateWallet(int id, WalletDto walletDto) throws Exception {
        User user = userRepository.findById(walletDto.getUserId())
                .orElseThrow(Exception::new);
        AccountType accountType = accountTypeRepository.findById(walletDto.getAccountTypeId())
                .orElseThrow(Exception::new);
        Wallet wallet = walletRepository.findById(id)
                        .orElseThrow(Exception::new);

        wallet.setName(walletDto.getName());
        wallet.setDescription(walletDto.getDescription());
        wallet.setBalance(walletDto.getBalance());
        wallet.setUser((user));
        wallet.setAccountType(accountType);
        Wallet updatedWallet = walletRepository.save(wallet);

        return modelMapper.map(updatedWallet, WalletDto.class);
    }

    public Wallet updateWallet(Integer id, WalletDto walletDto) throws Exception {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(Exception::new);

        if (walletDto.getName() != null) {
            wallet.setName(walletDto.getName());
        }

        if (walletDto.getBalance() != null) {
            wallet.setBalance(walletDto.getBalance());
        }

        if (walletDto.getDescription() != null) {
            wallet.setDescription(walletDto.getDescription());
        }

        if(walletDto.getUserId() != null) {
            User user = userRepository.findById(walletDto.getUserId())
                    .orElseThrow(Exception::new);
            wallet.setUser(user);
        }

        if(walletDto.getAccountTypeId() != null) {
            AccountType accountType = accountTypeRepository.findById(walletDto.getAccountTypeId())
                    .orElseThrow(Exception::new);
            wallet.setAccountType(accountType);
        }

        return walletRepository.save(wallet);
    }

    public boolean deleteWallet(Integer id) {
        if (walletRepository.existsById(id)) {
            walletRepository.deleteById(id);
            return true;
        } else return false;
    }
}
