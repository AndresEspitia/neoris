package com.test.neoris.entities;

import com.test.neoris.models.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "customers")
public class Customer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", unique = true)
    private int clientId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private boolean status;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "identification")
    private String identification;
}
