package com.shopsphere.dao;

import com.shopsphere.model.CouponUsage;
import com.shopsphere.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponUsageDAO {

    public boolean addCouponUsage(CouponUsage usage) {

        String sql =
                "INSERT INTO coupon_usage " +
                "(coupon_id, user_id, order_id) " +
                "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, usage.getCouponId());
            ps.setInt(2, usage.getUserId());
            ps.setInt(3, usage.getOrderId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean addCouponUsage(
        CouponUsage usage,
        Connection con) {

    String sql =
            "INSERT INTO coupon_usage " +
            "(coupon_id, user_id, order_id) " +
            "VALUES (?, ?, ?)";

    try (PreparedStatement ps =
                 con.prepareStatement(sql)) {

        ps.setInt(1, usage.getCouponId());
        ps.setInt(2, usage.getUserId());
        ps.setInt(3, usage.getOrderId());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        e.printStackTrace();
        return false;
    }
}

    public List<CouponUsage> getUsageByUserId(int userId) {

        List<CouponUsage> usages =
                new ArrayList<>();

        String sql =
                "SELECT * FROM coupon_usage " +
                "WHERE user_id = ? " +
                "ORDER BY used_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    CouponUsage usage =
                            new CouponUsage();

                    usage.setUsageId(
                            rs.getInt("usage_id"));

                    usage.setCouponId(
                            rs.getInt("coupon_id"));

                    usage.setUserId(
                            rs.getInt("user_id"));

                    usage.setOrderId(
                            rs.getInt("order_id"));

                    if (rs.getTimestamp("used_at") != null) {

                        usage.setUsedAt(
                                rs.getTimestamp("used_at")
                                        .toLocalDateTime());
                    }

                    usages.add(usage);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return usages;
    }

    public List<CouponUsage> getUsageByCouponId(
            int couponId) {

        List<CouponUsage> usages =
                new ArrayList<>();

        String sql =
                "SELECT * FROM coupon_usage " +
                "WHERE coupon_id = ? " +
                "ORDER BY used_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, couponId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    CouponUsage usage =
                            new CouponUsage();

                    usage.setUsageId(
                            rs.getInt("usage_id"));

                    usage.setCouponId(
                            rs.getInt("coupon_id"));

                    usage.setUserId(
                            rs.getInt("user_id"));

                    usage.setOrderId(
                            rs.getInt("order_id"));

                    if (rs.getTimestamp("used_at") != null) {

                        usage.setUsedAt(
                                rs.getTimestamp("used_at")
                                        .toLocalDateTime());
                    }

                    usages.add(usage);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return usages;
    }
}