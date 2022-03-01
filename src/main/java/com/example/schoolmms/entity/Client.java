package com.example.schoolmms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private Date dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password; // cript encoder

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orderList;
}
