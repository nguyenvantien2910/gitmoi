package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.dao.iplm.CartDaoImpl;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.ShoppingCart;
import com.ra.projectmd03_nhom4.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ShoppingCartService implements ICartService {
    @Autowired
    private CartDaoImpl cartDao;
    @Autowired
    private HttpSession session;


    @Override
    public List<ShoppingCart> findCartByUserId(Long userId) {
        List<ShoppingCart> cartList = cartDao.findCartByUserId(userId);
        session.setAttribute("cartList", cartList);
        int totalCart = 0;
        for (ShoppingCart shoppingCart : cartList) {
            totalCart += shoppingCart.getOrderQuantity();
        }
        session.setAttribute("totalCart", totalCart);
        Float total = getCartTotal(cartList);
        session.setAttribute("total", total);
        return cartList;
    }

    @Override
    public ShoppingCart findCartById(Long shoppingCartId) {
        ShoppingCart cart = cartDao.findCartById(shoppingCartId);
        session.setAttribute("cart", cart);
        return cart;
    }

    @Override
    public boolean addToCart(Product product, Long user_id, Integer quantity) {
        return cartDao.addToCart(product, user_id, quantity);
    }

    @Override
    public void removeFromCart(Long cart_id) {
        cartDao.removeFromCart(cart_id);
    }

    @Override
    public void removeCartByUserId(Long user_id) {
        cartDao.removeCartByUserId(user_id);
    }

    @Override
    public void updateCartById(Long cart_id, Integer qty) {
        cartDao.updateCartById(cart_id, qty);
    }

    @Override
    public boolean updateCart(Product product, Long user_id, Integer quantity) {
        return cartDao.updateCart(product, user_id, quantity);
    }

    @Override
    public boolean checkExistProductInCart(Long product_id, Long user_id) {
        List<ShoppingCart> cartList = cartDao.findCartByUserId(user_id);
        for (ShoppingCart cart : cartList) {
            if (cart.getProduct().getProductId() == product_id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Float getCartTotal(List<ShoppingCart> shoppingCartList) {
        float total = 0f;
        for (ShoppingCart cart : shoppingCartList) {
            total += cart.getProduct().getUnitPrice().floatValue() * cart.getOrderQuantity();
        }
        return total;
    }
}
