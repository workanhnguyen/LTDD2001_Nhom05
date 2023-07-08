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
@Table(name = "category_root")
@NamedQueries({
    @NamedQuery(name = "CategoryRoot.findAll", query = "SELECT c FROM CategoryRoot c"),
    @NamedQuery(name = "CategoryRoot.findById", query = "SELECT c FROM CategoryRoot c WHERE c.id = :id"),
    @NamedQuery(name = "CategoryRoot.findByName", query = "SELECT c FROM CategoryRoot c WHERE c.name = :name"),
    @NamedQuery(name = "CategoryRoot.findByType", query = "SELECT c FROM CategoryRoot c WHERE c.type = :type")})
public class CategoryRoot implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryRoot")
    private Set<CategoryType> categoryTypeSet;

    public CategoryRoot() {
    }

    public CategoryRoot(Integer id) {
        this.id = id;
    }

    public CategoryRoot(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<CategoryType> getCategoryTypeSet() {
        return categoryTypeSet;
    }

    public void setCategoryTypeSet(Set<CategoryType> categoryTypeSet) {
        this.categoryTypeSet = categoryTypeSet;
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
        if (!(object instanceof CategoryRoot)) {
            return false;
        }
        CategoryRoot other = (CategoryRoot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.backend.models.CategoryRoot[ id=" + id + " ]";
    }
    
}
