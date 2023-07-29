package com.example.backend.dtos;

import java.util.Date;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {
    private Integer id;
    private String description;
    private Long createdDate;
    private Long total;
    private String image;
    private Integer categoryTypeId;
    private Integer walletId;
}
