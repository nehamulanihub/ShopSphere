package com.shopsphere.dao;

import com.shopsphere.model.WishlistItem;
import com.shopsphere.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {

    public boolean addToWishlist(WishlistItem item) {

        String sql = "INSERT INTO wishlist " +
                "(user_id, product_id) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getUserId());
            ps.setInt(2, item.getProductId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<WishlistItem> getWishlistByUserId(int userId) {

        List<WishlistItem> wishlist = new ArrayList<>();

        String sql =
                "SELECT * FROM wishlist WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                WishlistItem item = new WishlistItem();

                item.setWishlistId(
                        rs.getInt("wishlist_id"));

                item.setUserId(
                        rs.getInt("user_id"));

                item.setProductId(
                        rs.getInt("product_id"));

                wishlist.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishlist;
    }

    public boolean removeFromWishlist(
            int wishlistId,
            int userId) {

        String sql =
                "DELETE FROM wishlist " +
                "WHERE wishlist_id = ? AND user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, wishlistId);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}