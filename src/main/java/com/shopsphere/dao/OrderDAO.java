package com.shopsphere.dao;

import com.shopsphere.model.Order;
import com.shopsphere.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public int createOrder(Order order) {

        String sql =
                "INSERT INTO orders " +
                "(user_id, address_id, total_amount, status) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getAddressId());
            ps.setBigDecimal(3, order.getTotalAmount());
            ps.setString(4, order.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs =
                             ps.getGeneratedKeys()) {

                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    public int createOrder(
            Order order,
            Connection con) {

        String sql =
                "INSERT INTO orders " +
                "(user_id, address_id, total_amount, status) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps =
                     con.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getAddressId());
            ps.setBigDecimal(3, order.getTotalAmount());
            ps.setString(4, order.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs =
                             ps.getGeneratedKeys()) {

                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    public Order getOrderById(int orderId) {

        String sql =
                "SELECT * FROM orders " +
                "WHERE order_id = ?";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {
                    return mapOrder(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders =
                new ArrayList<>();

        String sql =
                "SELECT * FROM orders " +
                "WHERE user_id = ? " +
                "ORDER BY order_date DESC";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    orders.add(
                            mapOrder(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


    public List<Order> getAllOrders() {

        List<Order> orders =
                new ArrayList<>();

        String sql =
                "SELECT * FROM orders " +
                "ORDER BY order_date DESC";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                orders.add(
                        mapOrder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


    public boolean updateOrderStatus(
            int orderId,
            String status) {

        String sql =
                "UPDATE orders SET status = ? " +
                "WHERE order_id = ?";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private Order mapOrder(
            ResultSet rs)
            throws SQLException {

        Order order =
                new Order();

        order.setOrderId(
                rs.getInt("order_id"));

        order.setUserId(
                rs.getInt("user_id"));

        order.setAddressId(
                rs.getInt("address_id"));

        order.setTotalAmount(
                rs.getBigDecimal(
                        "total_amount"));

        order.setStatus(
                rs.getString("status"));

        if (rs.getTimestamp(
                "order_date") != null) {

            order.setOrderDate(
                    rs.getTimestamp(
                            "order_date")
                            .toLocalDateTime());
        }

        return order;
    }
}