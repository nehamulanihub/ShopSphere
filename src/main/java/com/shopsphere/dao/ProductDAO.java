package com.shopsphere.dao;

import com.shopsphere.model.Product;
import com.shopsphere.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean addProduct(Product product) {

        String sql = "INSERT INTO products " +
                "(category_id, name, description, price, stock, image_url) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setBigDecimal(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.setString(6, product.getImageUrl());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product getProductById(int productId) {

        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapProduct(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(mapProduct(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> searchProducts(String keyword) {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products " +
                     "WHERE name LIKE ? OR description LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String search = "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                products.add(mapProduct(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> filterAndSortProducts(
        String keyword,
        int categoryId,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String sort) {

    List<Product> products = new ArrayList<>();

    StringBuilder sql =
            new StringBuilder(
                    "SELECT * FROM products WHERE 1=1");

    List<Object> parameters =
            new ArrayList<>();

    if (keyword != null &&
            !keyword.trim().isEmpty()) {

        sql.append(
                " AND (name LIKE ? OR description LIKE ?)");

        String search =
                "%" + keyword.trim() + "%";

        parameters.add(search);
        parameters.add(search);
    }

    if (categoryId > 0) {

        sql.append(" AND category_id = ?");

        parameters.add(categoryId);
    }

    if (minPrice != null) {

        sql.append(" AND price >= ?");

        parameters.add(minPrice);
    }

    if (maxPrice != null) {

        sql.append(" AND price <= ?");

        parameters.add(maxPrice);
    }

    if ("priceLow".equals(sort)) {

        sql.append(" ORDER BY price ASC");

    } else if ("priceHigh".equals(sort)) {

        sql.append(" ORDER BY price DESC");

    } else if ("nameAZ".equals(sort)) {

        sql.append(" ORDER BY name ASC");

    } else if ("nameZA".equals(sort)) {

        sql.append(" ORDER BY name DESC");

    } else {

        sql.append(" ORDER BY product_id DESC");
    }

    try (Connection con =
                 DBConnection.getConnection();
         PreparedStatement ps =
                 con.prepareStatement(sql.toString())) {

        for (int i = 0;
             i < parameters.size();
             i++) {

            ps.setObject(
                    i + 1,
                    parameters.get(i));
        }

        try (ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                products.add(
                        mapProduct(rs));
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return products;
}

    public boolean updateProduct(Product product) {

        String sql = "UPDATE products SET category_id = ?, name = ?, " +
                "description = ?, price = ?, stock = ?, image_url = ? " +
                "WHERE product_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setBigDecimal(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.setString(6, product.getImageUrl());
            ps.setInt(7, product.getProductId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(
        Product product,
        Connection con) {

    String sql =
            "UPDATE products SET " +
            "name = ?, " +
            "description = ?, " +
            "price = ?, " +
            "stock = ?, " +
            "category_id = ?, " +
            "image_url = ? " +
            "WHERE product_id = ?";

    try (PreparedStatement ps =
                 con.prepareStatement(sql)) {

        ps.setString(1, product.getName());
        ps.setString(2, product.getDescription());
        ps.setBigDecimal(3, product.getPrice());
        ps.setInt(4, product.getStock());
        ps.setInt(5, product.getCategoryId());
        ps.setString(6, product.getImageUrl());
        ps.setInt(7, product.getProductId());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        e.printStackTrace();
        return false;
    }
}

    public boolean deleteProduct(int productId) {

        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Product mapProduct(ResultSet rs) throws SQLException {

        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setStock(rs.getInt("stock"));
        product.setImageUrl(rs.getString("image_url"));

        return product;
    }
}
