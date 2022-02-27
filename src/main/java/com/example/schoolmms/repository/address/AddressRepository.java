package com.example.schoolmms.repository.address;

import com.example.schoolmms.entity.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> findAll();
    Address findById(long id);
    void save(Address address);
}
