/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "category_type")
@NamedQueries({
    @NamedQuery(name = "CategoryType.findAll", query = "SELECT c FROM CategoryType c"),
    @NamedQuery(name = "CategoryType.findById", query = "SELECT c FROM CategoryType c WHERE c.id = :id"),
    @NamedQuery(name = "CategoryType.findByName", query = "SELECT c FROM CategoryType c WHERE c.name = :name")})
public class CategoryType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "category_root", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoryRoot categoryRoot;
    @JsonIgnore
    @OneToMany(mappedBy = "categoryType")
    private Set<Transaction> transactionSet;

    public CategoryType() {
    }

    public CategoryType(Integer id) {
        this.id = id;
    }

    public CategoryType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryRoot getCategoryRoot() {
        return categoryRoot;
    }

    public void setCategoryRoot(CategoryRoot categoryRoot) {
        this.categoryRoot = categoryRoot;
    }

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Set<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoryType)) {
            return false;
        }
        CategoryType other = (CategoryType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.backend.models.CategoryType[ id=" + id + " ]";
    }
    
}
