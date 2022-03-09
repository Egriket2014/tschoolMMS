package com.example.schoolmms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diagonals")
@Data
public class Diagonal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diagonal_name")
    private Integer diagonal;

    @OneToMany(mappedBy = "diagonal", cascade = CascadeType.ALL)
    private List<Product> productList;
}
