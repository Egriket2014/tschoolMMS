package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(long id);
    Product findByName(String productTitle);
    void save(Product product);
    void update(Product product);
}
