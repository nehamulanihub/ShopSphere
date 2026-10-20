package com.shopsphere.service;

import com.shopsphere.dao.OrderDAO;
import com.shopsphere.dao.OrderItemDAO;

import com.shopsphere.model.Order;
import com.shopsphere.model.OrderItem;

import java.util.List;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();
    

    public int createOrder(Order order) {

        if (order == null ||
                order.getUserId() <= 0 ||
                order.getTotalAmount() == null) {
            return -1;
        }

        return orderDAO.createOrder(order);
    }

    public Order getOrderById(int orderId) {

        if (orderId <= 0) {
            return null;
        }

        return orderDAO.getOrderById(orderId);
    }

    public List<Order> getOrdersByUserId(int userId) {

        if (userId <= 0) {
            return List.of();
        }

        return orderDAO.getOrdersByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public boolean updateOrderStatus(
            int orderId,
            String status) {

        if (orderId <= 0 ||
                status == null ||
                status.trim().isEmpty()) {
            return false;
        }

        String normalizedStatus =
                status.trim().toUpperCase();

        if (!isValidStatus(normalizedStatus)) {
            return false;
        }

        return orderDAO.updateOrderStatus(
                orderId,
                normalizedStatus);
    }

    private boolean isValidStatus(String status) {

        return "PLACED".equals(status) ||
               "PROCESSING".equals(status) ||
               "SHIPPED".equals(status) ||
               "DELIVERED".equals(status) ||
               "CANCELLED".equals(status);
    }

    public boolean addOrderItem(OrderItem item) {

        if (item == null ||
                item.getOrderId() <= 0 ||
                item.getProductId() <= 0 ||
                item.getQuantity() <= 0) {
            return false;
        }

        return orderItemDAO.addOrderItem(item);
    }

    public List<OrderItem> getOrderItems(int orderId) {

        if (orderId <= 0) {
            return List.of();
        }

        return orderItemDAO.getItemsByOrderId(orderId);
    }
}