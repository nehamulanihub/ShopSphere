
package com.shopsphere.swingadmin.dao;

import com.shopsphere.model.Coupon;
import com.shopsphere.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AdminCouponDAO {

    public List<Coupon> getAllCoupons() {

        List<Coupon> coupons = new ArrayList<>();

        String sql =
                "SELECT coupon_id, code, discount_value AS discount, " +
                "expiry_date, active FROM coupons " +
                "ORDER BY coupon_id DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Coupon coupon = new Coupon();

                coupon.setCouponId(
                        resultSet.getInt("coupon_id"));

                coupon.setCode(
                        resultSet.getString("code"));

                coupon.setDiscount(
                        resultSet.getBigDecimal("discount"));

                Date expiryDate =
                        resultSet.getDate("expiry_date");

                if (expiryDate != null) {
                    coupon.setExpiryDate(
                            expiryDate.toLocalDate());
                }

                coupon.setActive(
                        resultSet.getBoolean("active"));

                coupons.add(coupon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupons;
    }

    public boolean deleteCoupon(int couponId) {

        String sql =
                "DELETE FROM coupons WHERE coupon_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, couponId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
