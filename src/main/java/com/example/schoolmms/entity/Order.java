package com.example.schoolmms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "payment_method")
    private Boolean paymentMethod; // Replace to enum later

    @Column(name = "delivery_method")
    private Boolean deliveryMethod; // Replace to enum later

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "order_product",
            joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    @Column(name = "payment_status")
    private Boolean paymentStatus; // Replace to enum later

    @Column(name = "order_status")
    private Boolean orderStatus; // Replace to enum later
}
