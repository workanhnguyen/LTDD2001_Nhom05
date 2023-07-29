package com.example.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
    private Integer id;
    private String name;
    private Long balance;
    private String description;
    private Integer userId;
    private Integer accountTypeId;
}
