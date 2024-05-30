package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Voucher;

import java.util.List;

public interface IVoucherDao {
    Long findAllCode(String code);
    Voucher findByCode(Long code);
    boolean save(Voucher voucher);
    boolean update(Voucher voucher);
    boolean delete(Voucher voucher);
    List<Voucher> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery);
}
