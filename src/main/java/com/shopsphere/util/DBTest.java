package com.shopsphere.util;

import java.sql.Connection;

public class DBTest {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            if (con != null) {
                System.out.println("Database Connection Successful!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
