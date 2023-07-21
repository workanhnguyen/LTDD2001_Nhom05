package com.example.backend.controllers;

import com.example.backend.daos.CategoryRootDao;
import com.example.backend.dtos.CategoryRootDto;
import com.example.backend.models.CategoryRoot;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/category-roots")
@CrossOrigin
public class CategoryRootController {
    @Autowired
    private CategoryRootDao categoryRootDao;

    @GetMapping
    public ResponseEntity<List<CategoryRoot>> getAllCategoryRoots() {
        return ResponseEntity.ok().body(categoryRootDao.getAllCategoryRoots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoryRoot>> getCategoryRoot(@PathVariable int id) {
        return ResponseEntity.ok().body(categoryRootDao.getCategoryRoot(id));
    }

    @PostMapping
    public ResponseEntity<CategoryRootDto> addNewCategoryRoot(@RequestBody CategoryRootDto categoryRootDto) throws Exception {
        return new ResponseEntity<>(categoryRootDao.addNewCategoryRoot(categoryRootDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRootDto> updateCategoryRoot (@PathVariable int id, @RequestBody CategoryRootDto categoryRootDto) throws Exception {
        return ResponseEntity.ok().body(categoryRootDao.updateCategoryRoot(id, categoryRootDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryRootDto> patchCategoryRoot (@PathVariable int id, @RequestBody CategoryRootDto categoryRootDto) throws Exception {
        return ResponseEntity.ok().body(categoryRootDao.patchCategoryRoot(id, categoryRootDto));
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategoryRoot(@PathVariable int id) {
        return categoryRootDao.deleteCategoryRoot(id);
    }


}


