package com.shopsphere.swingadmin.view;

import com.shopsphere.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminLowStockView extends JFrame {

    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JTextField limitField;
    private JButton checkButton;
    private JButton refreshButton;

    public AdminLowStockView() {

        setTitle("ShopSphere - Low Stock Products");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{
                        "Product ID",
                        "Product Name",
                        "Price",
                        "Stock"
                }, 0);

        stockTable = new JTable(tableModel);

        limitField = new JTextField("5", 8);

        checkButton = new JButton("Check Low Stock");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(new JLabel("Stock Limit:"));
        buttonPanel.add(limitField);
        buttonPanel.add(checkButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(stockTable),
                BorderLayout.CENTER);

        add(buttonPanel,
                BorderLayout.SOUTH);
    }

    public void displayProducts(List<Product> products) {

        tableModel.setRowCount(0);

        for (Product product : products) {

            tableModel.addRow(new Object[]{
                    product.getProductId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock()
            });
        }
    }

    public JTextField getLimitField() {
        return limitField;
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}
