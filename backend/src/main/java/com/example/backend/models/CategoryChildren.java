/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "category_children")
@NamedQueries({
    @NamedQuery(name = "CategoryChildren.findAll", query = "SELECT c FROM CategoryChildren c"),
    @NamedQuery(name = "CategoryChildren.findById", query = "SELECT c FROM CategoryChildren c WHERE c.id = :id"),
    @NamedQuery(name = "CategoryChildren.findByName", query = "SELECT c FROM CategoryChildren c WHERE c.name = :name")})
public class CategoryChildren implements Serializable {

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
    @Lob
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "category_root_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoryRoot categoryRootId;
    @OneToMany(mappedBy = "categoryChildrenId")
    private Set<Transaction> transactionSet;

    public CategoryChildren() {
    }

    public CategoryChildren(Integer id) {
        this.id = id;
    }

    public CategoryChildren(Integer id, String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryRoot getCategoryRootId() {
        return categoryRootId;
    }

    public void setCategoryRootId(CategoryRoot categoryRootId) {
        this.categoryRootId = categoryRootId;
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
        if (!(object instanceof CategoryChildren)) {
            return false;
        }
        CategoryChildren other = (CategoryChildren) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.backend.models.CategoryChildren[ id=" + id + " ]";
    }
    
}
