package com.shopsphere.swingadmin;

import com.shopsphere.swingadmin.controller.AdminDashboardController;
import com.shopsphere.swingadmin.view.AdminDashboardView;

import javax.swing.SwingUtilities;

public class ShopSphereAdmin {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            AdminDashboardView dashboardView =
                    new AdminDashboardView();

            new AdminDashboardController(
                    dashboardView
            );

            dashboardView.setVisible(true);
        });
    }
}