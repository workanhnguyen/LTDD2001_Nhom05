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
@Table(name = "account_children")
@NamedQueries({
    @NamedQuery(name = "AccountChildren.findAll", query = "SELECT a FROM AccountChildren a"),
    @NamedQuery(name = "AccountChildren.findById", query = "SELECT a FROM AccountChildren a WHERE a.id = :id"),
    @NamedQuery(name = "AccountChildren.findByName", query = "SELECT a FROM AccountChildren a WHERE a.name = :name")})
public class AccountChildren implements Serializable {

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
    @JoinColumn(name = "account_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AccountRoot accountTypeId;
    @OneToMany(mappedBy = "accountChildrenId")
    private Set<Wallet> walletSet;

    public AccountChildren() {
    }

    public AccountChildren(Integer id) {
        this.id = id;
    }

    public AccountChildren(Integer id, String name) {
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

    public AccountRoot getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(AccountRoot accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Set<Wallet> getWalletSet() {
        return walletSet;
    }

    public void setWalletSet(Set<Wallet> walletSet) {
        this.walletSet = walletSet;
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
        if (!(object instanceof AccountChildren)) {
            return false;
        }
        AccountChildren other = (AccountChildren) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.backend.models.AccountChildren[ id=" + id + " ]";
    }
    
}
