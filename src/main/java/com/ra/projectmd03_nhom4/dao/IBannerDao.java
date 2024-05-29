package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Banner;

import java.util.List;

public interface IBannerDao {
    List<Banner> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) ;
    List<Banner> findBannerToDisplay() ;
    Banner findById(Long id);
    void save(Banner banner);
    void deleteById(Long id);
    String getImageByBannerId(Long id);
    Long countAllBanner();
    boolean updateStatus(Long id,Boolean newStatus);
}
