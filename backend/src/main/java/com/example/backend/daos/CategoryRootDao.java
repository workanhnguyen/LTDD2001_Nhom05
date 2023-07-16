package com.example.backend.daos;


import com.example.backend.dtos.CategoryRootDto;

import com.example.backend.models.CategoryRoot;

import com.example.backend.repositories.CategoryRootRepository;
import com.example.backend.repositories.CategoryTypeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CategoryRootDao {
    @Autowired
    private CategoryTypeRepository categoryTypeRepository;
    @Autowired
    private CategoryRootRepository categoryRootRepository;
    private ModelMapper modelMapper;

    public List<CategoryRootDto> getAllCategoryRoots() {
        List<CategoryRoot> categoryRoots = categoryRootRepository.findAll();

        return categoryRoots.stream().map((a) -> modelMapper.map(a, CategoryRootDto.class))
                .collect(Collectors.toList());
    }

    public CategoryRootDto getCategoryRoot(int id) {
        Optional<CategoryRoot> categoryRoot = categoryRootRepository.findById(id);
        return modelMapper.map(categoryRoot, CategoryRootDto.class);
    }

    public boolean deleteCategoryRoot(int id) {
        if (categoryRootRepository.existsById(id)) {
            categoryRootRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }



    public CategoryRootDto addNewCategoryRoot(CategoryRootDto categoryRootDto) throws Exception {

        CategoryRoot categoryRoot = new CategoryRoot();
        categoryRoot.setName(categoryRootDto.getName());
        categoryRoot.setImage(categoryRootDto.getImage());
        categoryRoot.setType(categoryRootDto.getType());

        CategoryRoot savedCategoryRoot = categoryRootRepository.save(categoryRoot);

        return modelMapper.map(savedCategoryRoot, CategoryRootDto.class);
    }

    public CategoryRootDto updateCategoryRoot(int id, CategoryRootDto updatedCategoryRootDto) throws Exception {
        CategoryRoot categoryRoot = categoryRootRepository.findById(id)
                .orElseThrow(Exception::new);

        categoryRoot.setName(updatedCategoryRootDto.getName());
        categoryRoot.setImage(updatedCategoryRootDto.getImage());
        categoryRoot.setType(updatedCategoryRootDto.getType());

        CategoryRoot updatedAccountRoot = categoryRootRepository.save(categoryRoot);

        return modelMapper.map(updatedAccountRoot, CategoryRootDto.class);
    }

    public CategoryRootDto patchCategoryRoot(int id, CategoryRootDto updatedCategoryRootDto) throws Exception {
        CategoryRoot categoryRoot = categoryRootRepository.findById(id)
                .orElseThrow(Exception::new);

        if (updatedCategoryRootDto.getName() != null) {
            categoryRoot.setName(updatedCategoryRootDto.getName());
        }

        if (updatedCategoryRootDto.getImage() != null) {
            categoryRoot.setImage(updatedCategoryRootDto.getImage());
        }

        if (updatedCategoryRootDto.getType() != null) {
            categoryRoot.setType(updatedCategoryRootDto.getType());
        }

        CategoryRoot updatedCategoryRoot = categoryRootRepository.save(categoryRoot);

        return modelMapper.map(updatedCategoryRoot, CategoryRootDto.class);
    }


}


