package com.example.backend.controllers;

import com.example.backend.daos.CategoryTypeDao;
import com.example.backend.dtos.CategoryTypeDto;
import com.example.backend.models.CategoryType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/category-types")
@CrossOrigin
public class CategoryTypeController {
    @Autowired
    private CategoryTypeDao categoryTypeDao;

    @GetMapping
    public ResponseEntity<List<CategoryType>> getAllCategoryTypes() {
        return ResponseEntity.ok().body(categoryTypeDao.getAllCategoryTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoryType>> getCategoryType(@PathVariable int id) {
        return ResponseEntity.ok().body(categoryTypeDao.getCategoryType(id));
    }

    @PostMapping
    public ResponseEntity<CategoryTypeDto> addNewCategoryType(@RequestBody CategoryTypeDto categoryTypeDto) throws Exception {
        return new ResponseEntity<>(categoryTypeDao.addNewCategoryType(categoryTypeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryTypeDto> updateCategoryType (@PathVariable int id, @RequestBody CategoryTypeDto categoryTypeDto) throws Exception {
        return ResponseEntity.ok().body(categoryTypeDao.updateCategoryType(id, categoryTypeDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryTypeDto> patchCategoryType (@PathVariable int id, @RequestBody CategoryTypeDto categoryTypeDto) throws Exception {
        return ResponseEntity.ok().body(categoryTypeDao.patchCategoryType(id, categoryTypeDto));
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategoryType(@PathVariable int id) {
        return categoryTypeDao.deleteCategoryType(id);
    }


    @GetMapping(params = "categoryRootId")
    public ResponseEntity<List<CategoryType>> getAllAccountTypeFindByAccountRootID(@RequestParam("categoryRootId") int categoryRootId  ) throws Exception {
        return ResponseEntity.ok().body((List<CategoryType>) categoryTypeDao.getCategoryTypeByCategoryRoot(categoryRootId));
    }//

    @GetMapping(params = "name")
    public ResponseEntity<List<CategoryType>> getAllAccountTypeFindByName(@RequestParam("name") String name  ) throws Exception {
        return ResponseEntity.ok().body((List<CategoryType>) categoryTypeDao.getCategoryTypeByName(name));
    }//

    @GetMapping("/check-root-type/{id}")
    public ResponseEntity<String> getCheckRootTypeFindById(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body((String) categoryTypeDao.getTypeOfCategoryType(id));
    }//

}


