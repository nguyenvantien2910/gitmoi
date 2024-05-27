package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.ShoppingCart;

import java.util.List;

public interface ICartDao {
    List<ShoppingCart> findCartByUserId(Integer userId);
    boolean addToCart(Product product, Integer user_id, Integer quantity);
    void removeFromCart(Integer cart_id);
    void removeCartByUserId(Integer user_id);
    void updateCartById(Integer cart_id,Integer qty);
    boolean updateCart(Product product,Integer user_id,Integer quantity);
}
