package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Frequency;

import java.util.List;

public interface FrequencyRepository {
    List<Frequency> findAll();
    Frequency findById(long id);
    Frequency findByName(Integer frequency);
    void save(Frequency frequency);
}
