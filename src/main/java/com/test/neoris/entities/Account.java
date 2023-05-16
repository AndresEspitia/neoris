package com.test.neoris.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", unique = true)
    private int accountId;

    @Column(name = "number_account")
    private int numberAccount;

    @Column(name = "type")
    private String type;

    @Column(name = "initial_balance")
    private double initialBalance;

    @Column(name = "status")
    private boolean status;

    @Column(name = "customer")
    private int customer;

    @Column(name= "available_balance")
    private double availableBalance;

    public void initialize() {
        this.availableBalance = this.initialBalance;
    }

}
