package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Matrix;

import java.util.List;

public interface MatrixRepository {
    List<Matrix> findAll();
    Matrix findById(long id);
    Matrix findByName(String matrixName);
    void save(Matrix matrix);
}
