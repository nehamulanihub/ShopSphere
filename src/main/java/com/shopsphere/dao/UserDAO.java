package com.shopsphere.dao;

import com.shopsphere.model.User;
import com.shopsphere.util.DBConnection;
import com.shopsphere.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // =========================
    // REGISTER USER
    // =========================

    public boolean registerUser(User user) {

        String sql =
                "INSERT INTO users " +
                "(name, email, password, phone, role) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());

            String hashedPassword =
                    PasswordUtil.hashPassword(
                            user.getPassword());

            ps.setString(3, hashedPassword);
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getRole());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================
    // LOGIN USER
    // =========================

    public User loginUser(
            String email,
            String password) {

        String sql =
                "SELECT * FROM users " +
                "WHERE email = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            String hashedPassword =
                    PasswordUtil.hashPassword(password);

            ps.setString(2, hashedPassword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setUserId(
                        rs.getInt("user_id"));

                user.setName(
                        rs.getString("name"));

                user.setEmail(
                        rs.getString("email"));

                user.setPhone(
                        rs.getString("phone"));

                user.setPassword(
                        rs.getString("password"));

                user.setRole(
                        rs.getString("role"));

                if (rs.getTimestamp("created_at") != null) {

                    user.setCreatedAt(
                            rs.getTimestamp("created_at")
                                    .toLocalDateTime());
                }

                return user;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // CHECK EMAIL
    // =========================

    public boolean emailExists(String email) {

        String sql =
                "SELECT user_id FROM users " +
                "WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================
    // CHECK EMAIL FOR OTHER USER
    // =========================

    public boolean emailExistsForOtherUser(
            String email,
            int userId) {

        String sql =
                "SELECT user_id FROM users " +
                "WHERE email = ? AND user_id != ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setInt(2, userId);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================
    // UPDATE USER PROFILE
    // =========================

    public boolean updateUser(User user) {

        String sql =
                "UPDATE users SET " +
                "name = ?, " +
                "email = ?, " +
                "phone = ? " +
                "WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setInt(4, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================
    // GET ALL USERS
    // =========================

    public List<User> getAllUsers() {

        List<User> users =
                new ArrayList<>();

        String sql =
                "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                User user =
                        new User();

                user.setUserId(
                        rs.getInt("user_id"));

                user.setName(
                        rs.getString("name"));

                user.setEmail(
                        rs.getString("email"));

                user.setPhone(
                        rs.getString("phone"));

                user.setRole(
                        rs.getString("role"));

                if (rs.getTimestamp("created_at") != null) {

                    user.setCreatedAt(
                            rs.getTimestamp("created_at")
                                    .toLocalDateTime());
                }

                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }


    // =========================
    // CHANGE PASSWORD
    // =========================

    public boolean changePassword(
            int userId,
            String currentPassword,
            String newPassword) {

        String sql =
                "UPDATE users SET password = ? " +
                "WHERE user_id = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            String currentHashedPassword =
                    PasswordUtil.hashPassword(
                            currentPassword);

            String newHashedPassword =
                    PasswordUtil.hashPassword(
                            newPassword);

            ps.setString(
                    1,
                    newHashedPassword);

            ps.setInt(
                    2,
                    userId);

            ps.setString(
                    3,
                    currentHashedPassword);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}