package com.shopsphere.dao;

import com.shopsphere.model.Address;
import com.shopsphere.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {

    public boolean addAddress(Address address) {

        String sql = "INSERT INTO addresses " +
                "(user_id, address_line, city, state, pincode, country) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, address.getUserId());
            ps.setString(2, address.getAddressLine());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getState());
            ps.setString(5, address.getPincode());
            ps.setString(6, address.getCountry());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Address> getAddressesByUserId(int userId) {

        List<Address> addresses = new ArrayList<>();

        String sql =
                "SELECT * FROM addresses WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Address address = new Address();

                address.setAddressId(
                        rs.getInt("address_id"));

                address.setUserId(
                        rs.getInt("user_id"));

                address.setAddressLine(
                        rs.getString("address_line"));

                address.setCity(
                        rs.getString("city"));

                address.setState(
                        rs.getString("state"));

                address.setPincode(
                        rs.getString("pincode"));

                address.setCountry(
                        rs.getString("country"));

                addresses.add(address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    public boolean deleteAddress(
            int addressId,
            int userId) {

        String sql =
                "DELETE FROM addresses " +
                "WHERE address_id = ? AND user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, addressId);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}