package com.example.backend.repositories;

import com.example.backend.models.AccountRoot;
import com.example.backend.models.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    List<AccountType> findByAccountRoot(AccountRoot accountRoot);
}
