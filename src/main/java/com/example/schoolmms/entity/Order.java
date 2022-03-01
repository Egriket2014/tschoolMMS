package com.example.schoolmms.entity;

import com.example.schoolmms.entity.enums.DeliveryMethod;
import com.example.schoolmms.entity.enums.OrderStatus;
import com.example.schoolmms.entity.enums.PaymentMethod;
import com.example.schoolmms.entity.enums.PaymentStatus;
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "delivery_method")
    private DeliveryMethod deliveryMethod;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "order_product",
            joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "order_status")
    private OrderStatus orderStatus;
}
