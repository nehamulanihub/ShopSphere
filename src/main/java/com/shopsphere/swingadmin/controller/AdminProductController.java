package com.shopsphere.swingadmin.controller;

import com.shopsphere.model.Product;
import com.shopsphere.swingadmin.dao.AdminProductDAO;
import com.shopsphere.swingadmin.view.AdminProductView;

import javax.swing.*;
import java.util.List;

public class AdminProductController {

    private AdminProductView view;
    private AdminProductDAO dao;

    public AdminProductController(
            AdminProductView view,
            AdminProductDAO dao) {

        this.view = view;
        this.dao = dao;

        loadProducts();

        view.getRefreshButton()
                .addActionListener(e -> loadProducts());

        view.getDeleteButton()
                .addActionListener(e -> deleteProduct());
    }

    private void loadProducts() {

        List<Product> products =
                dao.getAllProducts();

        view.displayProducts(products);
    }

    private void deleteProduct() {

        int selectedRow =
                view.getProductTable().getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please select a product first.");

            return;
        }

        int productId =
                (int) view.getProductTable()
                        .getValueAt(selectedRow, 0);

        int choice =
                JOptionPane.showConfirmDialog(
                        view,
                        "Are you sure you want to delete this product?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {

            boolean deleted =
                    dao.deleteProduct(productId);

            if (deleted) {

                JOptionPane.showMessageDialog(
                        view,
                        "Product deleted successfully.");

                loadProducts();

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "Product could not be deleted.");
            }
        }
    }
}