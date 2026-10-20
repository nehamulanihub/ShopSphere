package com.shopsphere.swingadmin.dao;

import com.shopsphere.model.Product;
import com.shopsphere.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminLowStockDAO {

    public List<Product> getLowStockProducts(int limit) {

        List<Product> products = new ArrayList<>();

        String sql =
                "SELECT product_id, name, price, stock " +
                "FROM products " +
                "WHERE stock <= ? " +
                "ORDER BY stock ASC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, limit);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    Product product = new Product();

                    product.setProductId(
                            resultSet.getInt("product_id"));

                    product.setName(
                            resultSet.getString("name"));

                    product.setPrice(
                            resultSet.getBigDecimal("price"));

                    product.setStock(
                            resultSet.getInt("stock"));

                    products.add(product);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}