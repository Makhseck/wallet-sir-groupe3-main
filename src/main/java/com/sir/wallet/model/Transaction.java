package com.sir.wallet.model;

import jakarta.persistence.*;
import org.hibernate.service.spi.InjectService;


@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Wallet wallet;

    private long amount;
    private String type;

    @Column(name = "id_wallet",insertable = false,updatable = false)
    private Long walletId;

    public Transaction(long walletId,long amount, String type) {
        this.amount = amount;
        this.type = type;
        this.walletId = walletId;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }
    // Constructors, getters, and setters

    public Transaction() {
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
