package com.example.schoolmms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String productTitle;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "resolution_id")
    private Resolution resolution;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "diagonal_id")
    private Diagonal diagonal;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "matrix_id")
    private Matrix matrix;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "frequency_id")
    private Frequency frequency;

    @Column(name = "amount")
    private Integer amount;

    @ManyToMany(mappedBy = "products")
    private List<Order> orderList;
}
