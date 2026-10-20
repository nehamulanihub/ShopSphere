package com.shopsphere.swingadmin.dao;

import com.shopsphere.model.User;
import com.shopsphere.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminCustomerDAO {

    public List<User> getAllCustomers() {

        List<User> customers = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE role = 'CUSTOMER'";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User();

                user.setUserId(
                        resultSet.getInt("user_id"));

                user.setName(
                        resultSet.getString("name"));

                user.setEmail(
                        resultSet.getString("email"));

                user.setPhone(
                        resultSet.getString("phone"));

                user.setRole(
                        resultSet.getString("role"));

                customers.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    public boolean deleteCustomer(int userId) {

        String sql =
                "DELETE FROM users WHERE user_id = ? AND role = 'CUSTOMER'";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}