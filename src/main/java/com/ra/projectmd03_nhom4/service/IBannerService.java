package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.dto.request.BannerRequest;
import com.ra.projectmd03_nhom4.model.Banner;

import java.util.List;

public interface IBannerService {
    void save(BannerRequest bannerRequest);
    List<Banner> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery);
    Banner findById(Long id);
    void deleteById(Long id);
    String getImageByBannerId(Long id);
    Long countAllBanner();
    boolean updateStatus(Long id,Boolean newStatus);
    List<Banner> findBannerToDisplay() ;
}
