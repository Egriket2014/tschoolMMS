package com.example.schoolmms.repository.user;

import com.example.schoolmms.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(long id);
    User findByEmail(String email);
    void save(User user);
    void update(User user);
}
