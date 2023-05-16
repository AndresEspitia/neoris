package com.test.neoris.entities;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "movements")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id", unique = true)
    private int movementId;

    @Column(name = "number_account")
    private int numberAccount ;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private boolean status ;

    @Column(name = "movement")
    private String movement;

    @Column(name = "value_mov")
    private double value_mov ;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column(name = "account_id")
    private String accountId;
}
