package com.shopsphere.swingadmin.controller;

import com.shopsphere.model.Order;
import com.shopsphere.swingadmin.dao.AdminOrderDAO;
import com.shopsphere.swingadmin.view.AdminOrderView;

import javax.swing.*;
import java.util.List;

public class AdminOrderController {

    private AdminOrderView view;
    private AdminOrderDAO dao;

    public AdminOrderController(
            AdminOrderView view,
            AdminOrderDAO dao) {

        this.view = view;
        this.dao = dao;

        loadOrders();

        view.getRefreshButton()
                .addActionListener(e -> loadOrders());

        view.getUpdateButton()
                .addActionListener(e -> updateOrderStatus());
    }

    private void loadOrders() {

        List<Order> orders =
                dao.getAllOrders();

        view.displayOrders(orders);
    }

    private void updateOrderStatus() {

        int selectedRow =
                view.getOrderTable().getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please select an order first.");

            return;
        }

        int orderId =
                (int) view.getOrderTable()
                        .getValueAt(selectedRow, 0);

        String status =
                (String) view.getStatusComboBox()
                        .getSelectedItem();

        boolean updated =
                dao.updateOrderStatus(
                        orderId,
                        status
                );

        if (updated) {

            JOptionPane.showMessageDialog(
                    view,
                    "Order status updated successfully.");

            loadOrders();

        } else {

            JOptionPane.showMessageDialog(
                    view,
                    "Order status could not be updated.");
        }
    }
}