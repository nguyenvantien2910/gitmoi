package com.ra.projectmd03_nhom4.dao;

import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.ShoppingCart;

import java.util.List;

public interface ICartDao {
    List<ShoppingCart> findCartByUserId(Long userId);
    boolean addToCart(Product product, Long user_id, Integer quantity);
    void removeFromCart(Long cart_id);
    void removeCartByUserId(Long user_id);
    void updateCartById(Long cart_id,Integer qty);
    boolean updateCart(Product product,Long user_id,Integer quantity);
    ShoppingCart findCartById(Long cart_id);
}
