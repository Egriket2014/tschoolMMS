package com.example.schoolmms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resolutions")
@Data
public class Resolution {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resolution_name")
    private String resolution;

    @OneToMany(mappedBy = "resolution", cascade = CascadeType.ALL)
    private List<Product> productList;
}
