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
@Table(name = "account_root")
@NamedQueries({
    @NamedQuery(name = "AccountRoot.findAll", query = "SELECT a FROM AccountRoot a"),
    @NamedQuery(name = "AccountRoot.findById", query = "SELECT a FROM AccountRoot a WHERE a.id = :id"),
    @NamedQuery(name = "AccountRoot.findByName", query = "SELECT a FROM AccountRoot a WHERE a.name = :name")})
public class AccountRoot implements Serializable {

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
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountTypeId")
    private Set<AccountChildren> accountChildrenSet;

    public AccountRoot() {
    }

    public AccountRoot(Integer id) {
        this.id = id;
    }

    public AccountRoot(Integer id, String name) {
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

    public Set<AccountChildren> getAccountChildrenSet() {
        return accountChildrenSet;
    }

    public void setAccountChildrenSet(Set<AccountChildren> accountChildrenSet) {
        this.accountChildrenSet = accountChildrenSet;
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
        if (!(object instanceof AccountRoot)) {
            return false;
        }
        AccountRoot other = (AccountRoot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.backend.models.AccountRoot[ id=" + id + " ]";
    }
    
}
