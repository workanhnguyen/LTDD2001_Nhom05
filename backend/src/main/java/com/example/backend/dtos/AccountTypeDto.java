package com.example.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeDto {
    private Integer id;
    private String name;
    private String image;
    private String description;
    private Integer accountRootId;
}


