/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ASUS
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "created_date")
    private Long createdDate;
    @Column(name = "total")
    private Long total;
    @Lob
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "category_type", referencedColumnName = "id")
    @ManyToOne
    private CategoryType categoryType;
    @JoinColumn(name = "wallet", referencedColumnName = "id")
    @ManyToOne
    private Wallet wallet;
}
