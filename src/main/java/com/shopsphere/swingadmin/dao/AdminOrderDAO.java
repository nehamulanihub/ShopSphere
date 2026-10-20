package com.shopsphere.swingadmin.dao;

import com.shopsphere.model.Order;
import com.shopsphere.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminOrderDAO {

    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders ORDER BY order_id DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Order order = new Order();

                order.setOrderId(
                        resultSet.getInt("order_id"));

                order.setUserId(
                        resultSet.getInt("user_id"));

                order.setTotalAmount(
                        resultSet.getBigDecimal("total_amount"));

                order.setStatus(
                        resultSet.getString("status"));

                order.setOrderDate(
                        resultSet.getTimestamp("order_date")
                                .toLocalDateTime());

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    public boolean updateOrderStatus(
            int orderId,
            String status) {

        String sql =
                "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, orderId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}