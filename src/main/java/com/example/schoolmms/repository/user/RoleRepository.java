package com.example.schoolmms.repository.user;

import com.example.schoolmms.entity.Role;

public interface RoleRepository {
    Role findByName(String name);
    void save(Role role);
    Role findById(long id);
}
