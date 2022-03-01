package com.example.schoolmms.repository.order;

import com.example.schoolmms.entity.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAll();
    Order findById(long id);
    void save(Order order);
    void update(Order order);
    List<Order> findByUserId(long user_id);
}
