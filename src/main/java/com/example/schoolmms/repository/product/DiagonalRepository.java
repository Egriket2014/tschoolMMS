package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Diagonal;

import java.util.List;

public interface DiagonalRepository {
    List<Diagonal> findAll();
    Diagonal findById(long id);
    Diagonal findByName(Integer diagonal);
    void save(Diagonal diagonal);
}
