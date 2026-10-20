package com.shopsphere.swingadmin.view;

import com.shopsphere.model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminOrderView extends JFrame {

    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> statusComboBox;
    private JButton updateButton;
    private JButton refreshButton;

    public AdminOrderView() {

        setTitle("ShopSphere - Order Management");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{
                        "Order ID",
                        "User ID",
                        "Total Amount",
                        "Status",
                        "Order Date"
                }, 0);

        orderTable = new JTable(tableModel);

        statusComboBox = new JComboBox<>(
                new String[]{
                        "PLACED",
                        "CONFIRMED",
                        "SHIPPED",
                        "DELIVERED",
                        "CANCELLED"
                });

        updateButton = new JButton("Update Status");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(
                new JLabel("New Status:")
        );

        buttonPanel.add(statusComboBox);
        buttonPanel.add(updateButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(orderTable),
                BorderLayout.CENTER);

        add(buttonPanel,
                BorderLayout.SOUTH);
    }

    public void displayOrders(List<Order> orders) {

        tableModel.setRowCount(0);

        for (Order order : orders) {

            tableModel.addRow(new Object[]{
                    order.getOrderId(),
                    order.getUserId(),
                    order.getTotalAmount(),
                    order.getStatus(),
                    order.getOrderDate()
            });
        }
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}