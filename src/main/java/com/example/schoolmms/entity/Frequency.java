package com.example.schoolmms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "frequencies")
@Data
public class Frequency {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "frequency_name")
    private Integer resolution;

    @OneToMany(mappedBy = "frequency", cascade = CascadeType.ALL)
    private List<Product> productList;
}
