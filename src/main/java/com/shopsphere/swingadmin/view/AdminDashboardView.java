package com.shopsphere.swingadmin.view;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardView extends JFrame {

    private JButton productButton;
    private JButton customerButton;
    private JButton categoryButton;
    private JButton inventoryButton;
    private JButton orderButton;
    private JButton couponButton;
    private JButton exitButton;
    private JButton lowStockButton;
    private JButton salesButton;

    public AdminDashboardView() {

        setTitle("ShopSphere - Admin Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel =
                new JLabel("ShopSphere Admin Dashboard");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26));

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER);

        productButton =
                new JButton("Product Management");

        customerButton =
                new JButton("Customer Management");

        categoryButton =
                new JButton("Category Management");

        inventoryButton =
                new JButton("Inventory Management");

        orderButton =
                new JButton("Order Management");

        couponButton =
                new JButton("Coupon Management");
        
        lowStockButton =
        new JButton("Low Stock Management");

        salesButton =
        new JButton("Sales Summary");

        exitButton =
                new JButton("Exit");

        JPanel buttonPanel =
                new JPanel(new GridLayout(4, 2, 15, 15));

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 50, 30, 50));

        buttonPanel.add(productButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(categoryButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(couponButton);
        buttonPanel.add(lowStockButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(salesButton);

        setLayout(new BorderLayout());

        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    public JButton getProductButton() {
        return productButton;
    }

    public JButton getCustomerButton() {
        return customerButton;
    }

    public JButton getCategoryButton() {
        return categoryButton;
    }

    public JButton getInventoryButton() {
        return inventoryButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public JButton getCouponButton() {
        return couponButton;
    }

    public JButton getLowStockButton() {
    return lowStockButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getSalesButton() {
        return salesButton;
    }
}