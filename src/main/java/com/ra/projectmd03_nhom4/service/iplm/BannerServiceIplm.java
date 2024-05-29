package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.iplm.IBannerDaoIplm;
import com.ra.projectmd03_nhom4.dto.request.BannerRequest;
import com.ra.projectmd03_nhom4.model.Banner;
import com.ra.projectmd03_nhom4.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceIplm implements IBannerService {
    @Autowired
    IBannerDaoIplm iBannerDaoIplm;

    @Autowired
    FileService fileService;

    @Override
    public void save(BannerRequest bannerRequest) {
        Banner banner = Banner.builder()
                .id(bannerRequest.getId())
                .title(bannerRequest.getTitle())
                .isDisplay(bannerRequest.getIsDisplay())
                .build();
        if(bannerRequest.getId() == null) {
            banner.setUrl(fileService.uploadFileToServer(bannerRequest.getFile()));
        } else {
            if(bannerRequest.getFile() != null && bannerRequest.getFile().getSize() > 0) {
                banner.setUrl(fileService.uploadFileToServer(bannerRequest.getFile()));
            } else {
                banner.setUrl(iBannerDaoIplm.getImageByBannerId(bannerRequest.getId()));
            }
        }
        iBannerDaoIplm.save(banner);
    }

    @Override
    public List<Banner> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String searchQuery) {
        return iBannerDaoIplm.findAll(pageNo, pageSize, sortField, sortDirection, searchQuery);
    }

    @Override
    public Banner findById(Long id) {
        return iBannerDaoIplm.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        iBannerDaoIplm.deleteById(id);
    }

    @Override
    public String getImageByBannerId(Long id) {
        return iBannerDaoIplm.getImageByBannerId(id);
    }

    @Override
    public Long countAllBanner() {
        return iBannerDaoIplm.countAllBanner();
    }

    @Override
    public boolean updateStatus(Long id, Boolean newStatus) {
        return iBannerDaoIplm.updateStatus(id, newStatus);
    }

    @Override
    public List<Banner> findBannerToDisplay() {
        return iBannerDaoIplm.findBannerToDisplay();
    }
}
