package com.example.backend.daos;


import com.example.backend.dtos.CategoryTypeDto;
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

@Service
@AllArgsConstructor
@Transactional
public class CategoryTypeDao {
    @Autowired
    private CategoryTypeRepository categoryTypeRepository;
    @Autowired
    private CategoryRootRepository categoryRootRepository;
    private ModelMapper modelMapper;

    public List<CategoryType> getAllCategoryTypes() {
        List<CategoryType> categoryTypes = categoryTypeRepository.findAll();

//        return categoryTypes.stream().map((a) -> modelMapper.map(a, CategoryTypeDto.class))
//                .collect(Collectors.toList());
        return categoryTypes;
    }

    public Optional<CategoryType> getCategoryType(int id) {
        Optional<CategoryType> categoryType = categoryTypeRepository.findById(id);
//        return modelMapper.map(categoryType, CategoryTypeDto.class);
        return categoryType;
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


}


