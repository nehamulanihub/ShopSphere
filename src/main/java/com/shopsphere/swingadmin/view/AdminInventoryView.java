package com.shopsphere.swingadmin.view;

import com.shopsphere.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminInventoryView extends JFrame {

    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private JTextField stockField;
    private JButton updateButton;
    private JButton refreshButton;

    public AdminInventoryView() {

        setTitle("ShopSphere - Inventory Management");
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

        inventoryTable = new JTable(tableModel);

        stockField = new JTextField(10);

        updateButton = new JButton("Update Stock");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(new JLabel("New Stock:"));
        buttonPanel.add(stockField);
        buttonPanel.add(updateButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(inventoryTable),
                BorderLayout.CENTER);

        add(buttonPanel,
                BorderLayout.SOUTH);
    }

    public void displayInventory(List<Product> products) {

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

    public JTable getInventoryTable() {
        return inventoryTable;
    }

    public JTextField getStockField() {
        return stockField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}