package com.shopsphere.swingadmin.view;

import com.shopsphere.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminProductView extends JFrame {

    private JTable productTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton refreshButton;

    public AdminProductView() {

        setTitle("ShopSphere - Admin Product Management");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Name",
                        "Price",
                        "Stock",
                        "Category ID"
                }, 0);

        productTable = new JTable(tableModel);

        deleteButton = new JButton("Delete Product");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(productTable),
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
                    product.getStock(),
                    product.getCategoryId()
            });
        }
    }

    public JTable getProductTable() {
        return productTable;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}
