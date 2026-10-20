package com.shopsphere.swingadmin.controller;

import com.shopsphere.model.Product;
import com.shopsphere.swingadmin.dao.AdminInventoryDAO;
import com.shopsphere.swingadmin.view.AdminInventoryView;

import javax.swing.*;
import java.util.List;

public class AdminInventoryController {

    private AdminInventoryView view;
    private AdminInventoryDAO dao;

    public AdminInventoryController(
            AdminInventoryView view,
            AdminInventoryDAO dao) {

        this.view = view;
        this.dao = dao;

        loadInventory();

        view.getRefreshButton()
                .addActionListener(e -> loadInventory());

        view.getUpdateButton()
                .addActionListener(e -> updateStock());
    }

    private void loadInventory() {

        List<Product> products =
                dao.getInventory();

        view.displayInventory(products);
    }

    private void updateStock() {

        int selectedRow =
                view.getInventoryTable().getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please select a product first.");

            return;
        }

        String stockText =
                view.getStockField().getText().trim();

        if (stockText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please enter the new stock.");

            return;
        }

        try {

            int stock =
                    Integer.parseInt(stockText);

            if (stock < 0) {

                JOptionPane.showMessageDialog(
                        view,
                        "Stock cannot be negative.");

                return;
            }

            int productId =
                    (int) view.getInventoryTable()
                            .getValueAt(selectedRow, 0);

            boolean updated =
                    dao.updateStock(
                            productId,
                            stock
                    );

            if (updated) {

                JOptionPane.showMessageDialog(
                        view,
                        "Stock updated successfully.");

                view.getStockField().setText("");

                loadInventory();

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "Stock could not be updated.");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please enter a valid number.");
        }
    }
}