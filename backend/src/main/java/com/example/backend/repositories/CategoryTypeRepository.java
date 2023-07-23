package com.example.backend.repositories;

import com.example.backend.models.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Integer> {
}
