package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Product;
import com.example.schoolmms.repository.product.searchCriteria.SearchCriteria;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findAllActive();
    Product findById(long id);
    List<Product> findByParam(List<SearchCriteria> params);
    void save(Product product);
    void update(Product product);
}
