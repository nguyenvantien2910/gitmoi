package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.IWishListDao;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.WishList;
import com.ra.projectmd03_nhom4.service.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements IWishListService {
    @Autowired
    private IWishListDao wishListDao;

    @Override
    public List<Product> getAllWishList(Long userId) {
        return wishListDao.getAllWishList(userId);
    }

    @Override
    public boolean addWishList(WishList wishList) {
        return wishListDao.addWishList(wishList);
    }

    @Override
    public void deleteWishList(Long userId , Long productId) {
        wishListDao.deleteWishList(userId , productId);
    }
}
