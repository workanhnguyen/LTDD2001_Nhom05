package com.example.backend.repositories;

import com.example.backend.models.CategoryRoot;
import com.example.backend.models.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Integer> {

    List<CategoryType> findByCategoryRoot(CategoryRoot categoryRoot);
    List<CategoryType> findByNameContaining(String nameCategoryType);
}
