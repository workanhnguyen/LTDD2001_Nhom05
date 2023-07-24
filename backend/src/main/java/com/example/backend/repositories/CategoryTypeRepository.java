package com.example.backend.repositories;

import com.example.backend.models.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Integer> {
    @Query("SELECT c FROM CategoryType c WHERE c.categoryRoot.type LIKE 'EXPENSE'")
    List<CategoryType> getExpenseCategories();
    @Query("SELECT c FROM CategoryType c WHERE c.categoryRoot.type LIKE 'INCOME'")
    List<CategoryType> getIncomeCategories();
}
