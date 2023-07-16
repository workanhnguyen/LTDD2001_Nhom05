package com.example.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryTypeDto {
    private Integer id;
    private String name;
    private String image;
    private Integer categoryRootId;
}
