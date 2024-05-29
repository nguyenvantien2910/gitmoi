package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.model.Address;

import java.util.List;

public interface IAddressService {
    List<Address> findAll();
    Address findById(Long id);
    List<Address> findByUserId(Long id);
    boolean addNew(Address address);
    boolean update(Address address);
    boolean delete(Long id);
}
