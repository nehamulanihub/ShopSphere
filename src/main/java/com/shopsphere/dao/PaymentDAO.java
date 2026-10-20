package com.shopsphere.dao;

import com.shopsphere.model.Payment;
import com.shopsphere.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PaymentDAO {

    public boolean createPayment(Payment payment) {

        String sql =
                "INSERT INTO payments " +
                "(order_id, payment_method, payment_status) " +
                "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    payment.getOrderId());

            ps.setString(
                    2,
                    payment.getPaymentMethod());

            ps.setString(
                    3,
                    payment.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    public boolean createPayment(
            Payment payment,
            Connection con) {

        String sql =
                "INSERT INTO payments " +
                "(order_id, payment_method, payment_status) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    payment.getOrderId());

            ps.setString(
                    2,
                    payment.getPaymentMethod());

            ps.setString(
                    3,
                    payment.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    public Payment getPaymentByOrderId(
            int orderId) {

        String sql =
                "SELECT * FROM payments " +
                "WHERE order_id = ?";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Payment payment =
                        new Payment();

                payment.setPaymentId(
                        rs.getInt("payment_id"));

                payment.setOrderId(
                        rs.getInt("order_id"));

                payment.setPaymentMethod(
                        rs.getString(
                                "payment_method"));

                payment.setStatus(
                        rs.getString(
                                "payment_status"));

                return payment;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}