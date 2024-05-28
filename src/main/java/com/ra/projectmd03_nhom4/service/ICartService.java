package com.ra.projectmd03_nhom4.service;

import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.ShoppingCart;

import java.util.List;

public interface ICartService {

    List<ShoppingCart> findCartByUserId(Long userId);

    ShoppingCart findCartById(Long shoppingCartId);

    boolean addToCart(Product product, Long user_id, Integer quantity);

    void removeFromCart(Long cart_id);

    void removeCartByUserId(Long user_id);

    void updateCartById(Long cart_id, Integer qty);

    boolean updateCart(Product product, Long user_id, Integer quantity);

    boolean checkExistProductInCart(Long product_id, Long user_id);
    Float getCartTotal(List<ShoppingCart> shoppingCartList);
}
