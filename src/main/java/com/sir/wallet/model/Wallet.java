package com.sir.wallet.model;


import jakarta.persistence.Entity;

import jakarta.persistence.Id;


@Entity
public class Wallet {

    @Id
    private long id;
    private String name;
    private long balance;

    // Constructors, getters, and setters

    public Wallet() {
    }

    public Wallet(long id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Wallet(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
