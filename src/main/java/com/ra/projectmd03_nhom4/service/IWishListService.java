package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.WishList;

import java.util.List;

public interface IWishListService {
    public List<Product> getAllWishList(Long userId);
    public boolean addWishList(WishList wishList);
    public void deleteWishList(Long userId , Long productId);
}
