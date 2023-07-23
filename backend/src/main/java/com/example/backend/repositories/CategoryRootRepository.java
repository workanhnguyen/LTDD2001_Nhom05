package com.example.backend.repositories;

import com.example.backend.models.CategoryRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRootRepository extends JpaRepository<CategoryRoot, Integer> {
    CategoryRoot findByName(String categoryRoot);
}

