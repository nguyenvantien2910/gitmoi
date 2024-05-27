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
    public List<ShoppingCart> findCartByUserId(Integer userId) {
        List<ShoppingCart> cartList = cartDao.findCartByUserId(userId);
        session.setAttribute("cartList", cartList);
        Float total = getCartTotal(cartList);
        session.setAttribute("total", total);
        return cartList;
    }

    @Override
    public boolean addToCart(Product product, Integer user_id, Integer quantity) {
        return cartDao.addToCart(product, user_id, quantity);
    }

    @Override
    public void removeFromCart(Integer cart_id) {
        cartDao.removeFromCart(cart_id);
    }

    @Override
    public void removeCartByUserId(Integer user_id) {
        cartDao.removeCartByUserId(user_id);
    }

    @Override
    public void updateCartById(Integer cart_id, Integer qty) {
        cartDao.updateCartById(cart_id, qty);
    }

    @Override
    public boolean updateCart(Product product, Integer user_id, Integer quantity) {
        return cartDao.updateCart(product, user_id, quantity);
    }

    @Override
    public boolean checkExistProductInCart(Long product_id, Integer user_id) {
        List<ShoppingCart> cartList = cartDao.findCartByUserId(user_id);
        for (ShoppingCart cart : cartList) {
            if (cart.getProduct().getProductId() == product_id){
                return true;
            }
        }
        return false;
    }

    @Override
    public Float getCartTotal(List<ShoppingCart> shoppingCartList) {
        Float total = 0f;
        for (ShoppingCart cart : shoppingCartList) {
            total += cart.getProduct().getUnitPrice().floatValue()*cart.getOrderQuantity();
        }
        return total;
    }
}
