package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Resolution;

import java.util.List;

public interface ResolutionRepository {
    List<Resolution> findAll();
    Resolution findById(long id);
    Resolution findByName(String resolution);
    void save(Resolution resolution);
}
