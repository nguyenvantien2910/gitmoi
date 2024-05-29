package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.constant.OrderStatus;
import com.ra.projectmd03_nhom4.dao.IOrderDetailDAO;
import com.ra.projectmd03_nhom4.dao.IProductDao;
import com.ra.projectmd03_nhom4.dao.iplm.OrderDAOImpl;
import com.ra.projectmd03_nhom4.model.Order;
import com.ra.projectmd03_nhom4.model.OrderDetail;
import com.ra.projectmd03_nhom4.model.Product;
import com.ra.projectmd03_nhom4.model.ShoppingCart;
import com.ra.projectmd03_nhom4.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderDAOImpl orderDAO;
    @Autowired
    private HttpSession session;
    @Autowired
    private IOrderDetailDAO orderDetailDAO;
    @Autowired
    private IProductDao productDAO;

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderDAO.getOrdersByUserId(userId);
    }

    @Override
    public boolean addOrder(Order order) {
        try{
            Order newOrder = orderDAO.getOrderById(orderDAO.saveOrder(order));
            List<ShoppingCart> cartList = (List<ShoppingCart>) session.getAttribute("cartList");
            for (ShoppingCart cart : cartList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(newOrder);
                orderDetail.setProduct(cart.getProduct());
                orderDetail.setUnitPrice(cart.getProduct().getUnitPrice());
                orderDetail.setOrderQuantity(cart.getOrderQuantity());
                orderDetailDAO.save(orderDetail);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderDAO.cancelOrder(orderId);
        List<OrderDetail> orderDetailList = findDetailByOrderId(orderId);
        for (OrderDetail orderDetail : orderDetailList) {
            Product product = productDAO.findById(orderDetail.getProduct().getProductId());
            product.setStockQuantity(product.getStockQuantity() + orderDetail.getOrderQuantity());
        }
    }

    @Override
    public void acceptOrder(Long orderId) {
        orderDAO.acceptOrder(orderId);
    }

    @Override
    public boolean orderCheckout(Order order) {
        try{
            Order newOrder = orderDAO.getOrderById(orderDAO.saveOrder(order));
            List<ShoppingCart> cartList = (List<ShoppingCart>) session.getAttribute("cartList");
            for (ShoppingCart cart : cartList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(newOrder);
                orderDetail.setProduct(cart.getProduct());
                orderDetail.setUnitPrice(cart.getProduct().getUnitPrice());
                orderDetail.setOrderQuantity(cart.getOrderQuantity());
                orderDetailDAO.save(orderDetail);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OrderDetail> findDetailByOrderId(Long orderId) {
        return orderDetailDAO.getOrderDetail(orderId);
    }

    public boolean updateStatus(Order order) {
        return orderDAO.updateOrder(order);
    }
}
