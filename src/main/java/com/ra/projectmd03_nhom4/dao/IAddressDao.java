package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Address;

import java.util.List;

public interface IAddressDao {
    List<Address> findAll();
    Address findById(Long id);
    List<Address> findByUserId(Long id);
    boolean addNew(Address address);
    boolean update(Address address);
    boolean delete(Long id);
}
