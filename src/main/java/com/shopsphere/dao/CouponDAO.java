
package com.shopsphere.dao;

import com.shopsphere.model.Coupon;
import com.shopsphere.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CouponDAO {

    public boolean addCoupon(Coupon coupon) {

        String sql =
                "INSERT INTO coupons " +
                "(code, discount_type, discount_value, " +
                "min_order_amount, expiry_date, active) " +
                "VALUES (?, 'FLAT', ?, 0, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, coupon.getCode());
            ps.setBigDecimal(2, coupon.getDiscount());

            if (coupon.getExpiryDate() != null) {
                ps.setDate(3, Date.valueOf(coupon.getExpiryDate()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.setBoolean(4, coupon.isActive());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Coupon getCouponByCode(String code) {

        String sql =
                "SELECT coupon_id, code, discount_value AS discount, " +
                "expiry_date, active FROM coupons WHERE code = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapCoupon(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Coupon> getAllCoupons() {

        List<Coupon> coupons = new ArrayList<>();

        String sql =
                "SELECT coupon_id, code, discount_value AS discount, " +
                "expiry_date, active FROM coupons " +
                "ORDER BY coupon_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                coupons.add(mapCoupon(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupons;
    }

    public boolean updateCoupon(Coupon coupon) {

        String sql =
                "UPDATE coupons SET code = ?, " +
                "discount_value = ?, expiry_date = ?, active = ? " +
                "WHERE coupon_id = ? AND discount_type = 'FLAT'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, coupon.getCode());
            ps.setBigDecimal(2, coupon.getDiscount());

            if (coupon.getExpiryDate() != null) {
                ps.setDate(3, Date.valueOf(coupon.getExpiryDate()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.setBoolean(4, coupon.isActive());
            ps.setInt(5, coupon.getCouponId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCoupon(int couponId) {

        String sql =
                "DELETE FROM coupons WHERE coupon_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, couponId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Coupon mapCoupon(ResultSet rs)
            throws SQLException {

        Coupon coupon = new Coupon();

        coupon.setCouponId(rs.getInt("coupon_id"));
        coupon.setCode(rs.getString("code"));
        coupon.setDiscount(rs.getBigDecimal("discount"));

        Date expiryDate = rs.getDate("expiry_date");

        if (expiryDate != null) {
            coupon.setExpiryDate(expiryDate.toLocalDate());
        }

        coupon.setActive(rs.getBoolean("active"));

        return coupon;
    }
}