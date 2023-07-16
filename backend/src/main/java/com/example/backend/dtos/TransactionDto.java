package com.example.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Integer id;
    private String description;
    private Date createdDate;
    private Long total;
    private String image;
    private Integer categoryTypeId;
    private Integer walletId;
}
