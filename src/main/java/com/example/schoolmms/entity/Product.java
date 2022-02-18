package com.example.schoolmms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String productTitle;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String productCategory; // Replace to enum later

    @Column(name = "weight")
    private Double productWeight;

    @Column(name = "volume")
    private Double productVolume; // maybe delete

    @Column(name = "amount")
    private Integer amount;

    @ManyToMany(mappedBy = "products")
    private List<Order> orderList;
}
