package com.example.schoolmms.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String countryName;

    @Column(name = "city")
    private String cityName;

    @Column(name = "postal_code")
    private Long code;

    @Column(name = "street")
    private String streetName;

    @Column(name = "home")
    private Integer homeNumber;

    @Column(name = "apartment")
    private Integer apartmentNumber;
}
