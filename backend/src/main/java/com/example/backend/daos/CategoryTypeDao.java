package com.example.backend.daos;


import com.example.backend.dtos.AccountTypeDto;
import com.example.backend.dtos.CategoryRootDto;
import com.example.backend.dtos.CategoryTypeDto;
import com.example.backend.models.AccountRoot;
import com.example.backend.models.AccountType;
import com.example.backend.models.CategoryRoot;
import com.example.backend.models.CategoryType;
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
public class CategoryTypeDao {
    @Autowired
    private CategoryTypeRepository categoryTypeRepository;
    @Autowired
    private CategoryRootRepository categoryRootRepository;
    private ModelMapper modelMapper;

    public List<CategoryTypeDto> getAllCategoryTypes() {
        List<CategoryType> categoryTypes = categoryTypeRepository.findAll();

        return categoryTypes.stream().map((a) -> modelMapper.map(a, CategoryTypeDto.class))
                .collect(Collectors.toList());
    }

    public CategoryTypeDto getCategoryType(int id) {
        Optional<CategoryType> categoryType = categoryTypeRepository.findById(id);
        return modelMapper.map(categoryType, CategoryTypeDto.class);
    }

    public boolean deleteCategoryType(int id) {
        if (categoryTypeRepository.existsById(id)) {
            categoryTypeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }



    public CategoryTypeDto addNewCategoryType(CategoryTypeDto categoryTypeDto) throws Exception {
        CategoryRoot categoryRoot = categoryRootRepository.findById(categoryTypeDto.getCategoryRootId())
                .orElseThrow(Exception::new);

        CategoryType categoryType = new CategoryType();
        categoryType.setName(categoryTypeDto.getName());
        categoryType.setImage(categoryType.getImage());
        categoryType.setCategoryRoot(categoryRoot);

        return modelMapper.map(categoryTypeRepository.save(categoryType), CategoryTypeDto.class);

    }

    public CategoryTypeDto updateCategoryType(int id, CategoryTypeDto categoryTypeDto) throws Exception {
        CategoryType categoryType = categoryTypeRepository.findById(id)
                .orElseThrow(Exception::new);

        categoryType.setName(categoryTypeDto.getName());
        categoryType.setImage(categoryTypeDto.getImage());

        CategoryRoot categoryRoot = categoryRootRepository.findById(categoryTypeDto.getCategoryRootId())
                .orElseThrow(Exception::new);

        categoryType.setCategoryRoot(categoryRoot);
        CategoryType updatedCategoryType = categoryTypeRepository.save(categoryType);

        return modelMapper.map(updatedCategoryType, CategoryTypeDto.class);
    }

    public CategoryTypeDto patchCategoryType(int id, CategoryTypeDto categoryTypeDto) throws Exception {
        CategoryType categoryType = categoryTypeRepository.findById(id)
                .orElseThrow(Exception::new);

        if (categoryTypeDto.getName() != null) {
            categoryType.setName(categoryTypeDto.getName());
        }

        if (categoryTypeDto.getImage() != null) {
            categoryType.setImage(categoryTypeDto.getImage());
        }


        if(categoryTypeDto.getCategoryRootId() != null) {
            CategoryRoot categoryRoot = categoryRootRepository.findById(categoryTypeDto.getCategoryRootId())
                    .orElseThrow(Exception::new);
            categoryType.setCategoryRoot(categoryRoot);
        }

        CategoryType updatedCategoryType = categoryTypeRepository.save(categoryType);

        return modelMapper.map(updatedCategoryType, CategoryTypeDto.class);
    }

    public List<CategoryTypeDto> getCategoryTypeByCategoryRoot(int id) throws Exception {
        CategoryRoot categoryRoot = categoryRootRepository.findById(id)
                .orElseThrow(Exception::new);
        List<CategoryType> categoryTypes = categoryTypeRepository.findByCategoryRoot(categoryRoot);

        return categoryTypes.stream().map((a) -> modelMapper.map(a, CategoryTypeDto.class))
                .collect(Collectors.toList());
    }

    public List<CategoryTypeDto> getCategoryTypeByName(String name) throws Exception {

        List<CategoryType> categoryTypes = categoryTypeRepository.findByNameContaining(name);

        return categoryTypes.stream().map((a) -> modelMapper.map(a, CategoryTypeDto.class))
                .collect(Collectors.toList());
    }


    public String getTypeOfCategoryType(int id) throws Exception {
        CategoryType categoryType = categoryTypeRepository.findById(id)
                .orElseThrow(Exception::new);
//        CategoryRoot categoryRoot = categoryRootRepository.findById(categoryType.getCategoryRoot().getId())
//                .orElseThrow(Exception::new);
        return categoryType.getCategoryRoot().getType();
    }

}


