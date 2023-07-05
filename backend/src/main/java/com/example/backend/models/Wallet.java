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
@Table(name = "wallet")
@NamedQueries({
    @NamedQuery(name = "Wallet.findAll", query = "SELECT w FROM Wallet w"),
    @NamedQuery(name = "Wallet.findById", query = "SELECT w FROM Wallet w WHERE w.id = :id"),
    @NamedQuery(name = "Wallet.findByName", query = "SELECT w FROM Wallet w WHERE w.name = :name"),
    @NamedQuery(name = "Wallet.findByBalance", query = "SELECT w FROM Wallet w WHERE w.balance = :balance")})
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "balance")
    private long balance;
    @Lob
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "account_children_id", referencedColumnName = "id")
    @ManyToOne
    private AccountChildren accountChildrenId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(mappedBy = "walletId")
    private Set<Transaction> transactionSet;

    public Wallet() {
    }

    public Wallet(Integer id) {
        this.id = id;
    }

    public Wallet(Integer id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountChildren getAccountChildrenId() {
        return accountChildrenId;
    }

    public void setAccountChildrenId(AccountChildren accountChildrenId) {
        this.accountChildrenId = accountChildrenId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Wallet)) {
            return false;
        }
        Wallet other = (Wallet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.backend.models.Wallet[ id=" + id + " ]";
    }
    
}
