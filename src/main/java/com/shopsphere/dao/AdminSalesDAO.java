package com.shopsphere.dao;

import com.shopsphere.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;

public class AdminSalesDAO {

    public int getTotalOrders() {

        String sql =
                "SELECT COUNT(*) FROM orders " +
                "WHERE status <> 'CANCELLED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public BigDecimal getTotalSales() {

        String sql =
                "SELECT COALESCE(SUM(total_amount), 0) " +
                "FROM orders " +
                "WHERE status <> 'CANCELLED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }

    public BigDecimal getAverageOrderValue() {

        String sql =
                "SELECT COALESCE(AVG(total_amount), 0) " +
                "FROM orders " +
                "WHERE status <> 'CANCELLED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }
}