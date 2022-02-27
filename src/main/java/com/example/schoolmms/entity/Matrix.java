package com.example.schoolmms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "matrices")
@Data
public class Matrix {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matrix")
    private String matrix;

    @OneToMany(mappedBy = "matrix", cascade = CascadeType.ALL)
    private List<Product> productList;
}
