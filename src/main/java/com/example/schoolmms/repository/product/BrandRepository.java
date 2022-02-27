package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Brand;

import java.util.List;

public interface BrandRepository {
    List<Brand> findAll();
    Brand findById(long id);
    Brand findByName(String brandName);
    void save(Brand brand);
}
