package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.WishList;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IWishListDao {
    public List<Product> getAllWishList(Long userId);

    public boolean addWishList(WishList wishList);

    public void deleteWishList(Long userId, Long productId);
}
