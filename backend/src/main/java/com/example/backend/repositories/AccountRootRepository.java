package com.example.backend.repositories;

import com.example.backend.models.AccountRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRootRepository extends JpaRepository<AccountRoot, Integer> {
}
