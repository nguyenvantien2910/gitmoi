package com.ra.projectmd03_nhom3.service;

import java.util.List;

public interface IService <E,T,S,B,L> {
    List<E> findAll(T pageNo, T pageSize, S sortField, S sortDirection, S searchQuery);
    L count(S searchQuery);
    E findById(int id);
    boolean add(E e);
    boolean update(E e);
    boolean updateStatus(T id,B newStatus);
    boolean delete(T id);
}
