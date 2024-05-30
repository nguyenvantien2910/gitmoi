package com.ra.projectmd03_nhom4.service.iplm;

import com.ra.projectmd03_nhom4.config.EmailManagement;
import com.ra.projectmd03_nhom4.constant.OrderStatus;
import com.ra.projectmd03_nhom4.dao.IOrderDetailDAO;
import com.ra.projectmd03_nhom4.dao.IProductDao;
import com.ra.projectmd03_nhom4.dao.iplm.CartDaoImpl;
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
import java.util.UUID;

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
    @Autowired
    private MailService mailService;
    @Autowired
    private UserServiceIplm userService;
    @Autowired
    private CartDaoImpl cartDao;

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
        mailService.sendMail(EmailManagement.SENDER,
                userService.findById(order.getUser().getUserId()).getEmail(),
                EmailManagement.REGISTER_SUBJECT,
                EmailManagement.orderConfirmation(
                        userService.findById(order.getUser().getUserId()).getUsername(),
                        order.getReceivePhone(),
                        order.getReceiveName(),
                        order.getSerialNumber(),
                        order.getCreatedAt(),
                        order.getReceiveAddress()
                )

        );
        //#1965 :Tự động trừ kho khi đơn hàng được xác nhận.
        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetail(order.getOrderId());
        for (OrderDetail orderDetail : orderDetails) {
            Product product = orderDetail.getProduct();
            Integer newQuantity = product.getStockQuantity() - orderDetail.getOrderQuantity();
            if (newQuantity < 0) {
                newQuantity = 0;
            }
            product.setStockQuantity(newQuantity);
            productDAO.save(product);
        }
        return orderDAO.addOrder(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }

    //#1965 : Cập nhật kho khi hủy đơn hàng.
    @Override
    public void cancelOrder(Long orderId) {
        List<OrderDetail> orderDetailList = findDetailByOrderId(orderId);
        for (OrderDetail orderDetail : orderDetailList) {
            Product product = productDAO.findById(orderDetail.getProduct().getProductId());
            product.setStockQuantity(product.getStockQuantity() + orderDetail.getOrderQuantity());
        }
        orderDAO.cancelOrder(orderId);
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
        String trackingNumber = UUID.randomUUID().toString();
        mailService.sendMail(EmailManagement.SENDER,
                userService.findById(order.getUser().getUserId()).getEmail(),
                EmailManagement.CONFIRM_ORDER_SUBJECT,
                EmailManagement.orderShipmentConfirmation(
                        userService.findById(order.getUser().getUserId()).getUsername(),
                        order.getSerialNumber(),
                        trackingNumber,
                        "4 days",
                        "https://smartphone.com/"
                )
        );
        return orderDAO.updateOrder(order);
    }

    public void applyDiscount(Order order, double discount) {
        double totalPrice = order.getTotalPrice();
        order.setTotalPrice(totalPrice - (totalPrice * (discount / 100)));
    }

}
