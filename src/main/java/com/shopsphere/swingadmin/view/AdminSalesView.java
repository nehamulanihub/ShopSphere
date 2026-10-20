package com.shopsphere.swingadmin.view;

import javax.swing.*;
import java.awt.*;

public class AdminSalesView extends JFrame {

    private JLabel totalOrdersLabel;
    private JLabel totalSalesLabel;
    private JLabel deliveredOrdersLabel;
    private JButton refreshButton;

    public AdminSalesView() {

        setTitle("ShopSphere - Sales Summary");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel =
                new JLabel("ShopSphere Sales Summary");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24));

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER);

        totalOrdersLabel =
                new JLabel("Total Orders: 0");

        totalSalesLabel =
                new JLabel("Total Sales: ₹0.00");

        deliveredOrdersLabel =
                new JLabel("Delivered Orders: 0");

        totalOrdersLabel.setFont(
                new Font("Arial", Font.BOLD, 18));

        totalSalesLabel.setFont(
                new Font("Arial", Font.BOLD, 18));

        deliveredOrdersLabel.setFont(
                new Font("Arial", Font.BOLD, 18));

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(3, 1, 10, 10));

        summaryPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 50, 30, 50));

        summaryPanel.add(totalOrdersLabel);
        summaryPanel.add(totalSalesLabel);
        summaryPanel.add(deliveredOrdersLabel);

        refreshButton =
                new JButton("Refresh");

        setLayout(new BorderLayout());

        add(titleLabel, BorderLayout.NORTH);
        add(summaryPanel, BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);
    }

    public void displaySummary(
            int totalOrders,
            double totalSales,
            int deliveredOrders) {

        totalOrdersLabel.setText(
                "Total Orders: " + totalOrders);

        totalSalesLabel.setText(
                String.format(
                        "Total Sales: ₹%.2f",
                        totalSales));

        deliveredOrdersLabel.setText(
                "Delivered Orders: "
                        + deliveredOrders);
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}