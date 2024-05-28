package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.iplm.AddressDaoImpl;
import com.ra.projectmd03_nhom4.model.Address;
import com.ra.projectmd03_nhom4.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressDaoImpl addressDao;

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressDao.findById(id);
    }

    @Override
    public List<Address> findByUserId(Long id) {
        return addressDao.findByUserId(id);
    }

    @Override
    public boolean addNew(Address address) {
        return addressDao.addNew(address);
    }

    @Override
    public boolean update(Address address) {
        return addressDao.update(address);
    }

    @Override
    public boolean delete(Long id) {
        return addressDao.delete(id);
    }
}
