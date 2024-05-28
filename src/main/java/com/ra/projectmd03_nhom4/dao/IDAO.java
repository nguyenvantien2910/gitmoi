package com.ra.projectmd03_nhom4.dao;

import java.util.List;

public interface IDAO<E,T,S,B,L> {
    List<E> findAll(T pageNo, T pageSize, S sortField, S sortDirection, S searchQuery);
    L count(S searchQuery);
    E findById(L id);
    boolean add(E e);
    boolean update(E e);
    boolean updateStatus(L id,B newStatus);
    boolean delete(L id);
}
