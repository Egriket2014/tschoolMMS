package com.example.schoolmms.repository;

import java.util.Optional;

public interface IRepository<T, ID> {
    long count();
    Iterable<T> findAll();
    Optional<T> findById(ID id);
    void delete(T entity);
    void deleteById(ID id);
    void save(T entity);
    void saveAll(Iterable<T> entities);
    void update(T entity);
}
