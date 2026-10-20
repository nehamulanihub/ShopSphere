package com.shopsphere.swingadmin.controller;

import com.shopsphere.model.Product;
import com.shopsphere.swingadmin.dao.AdminLowStockDAO;
import com.shopsphere.swingadmin.view.AdminLowStockView;

import javax.swing.*;
import java.util.List;

public class AdminLowStockController {

    private AdminLowStockView view;
    private AdminLowStockDAO dao;

    public AdminLowStockController(
            AdminLowStockView view,
            AdminLowStockDAO dao) {

        this.view = view;
        this.dao = dao;

        loadLowStockProducts();

        view.getCheckButton()
                .addActionListener(e ->
                        loadLowStockProducts());

        view.getRefreshButton()
                .addActionListener(e ->
                        loadLowStockProducts());
    }

    private void loadLowStockProducts() {

        String limitText =
                view.getLimitField()
                        .getText()
                        .trim();

        if (limitText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please enter a stock limit.");

            return;
        }

        try {

            int limit =
                    Integer.parseInt(limitText);

            if (limit < 0) {

                JOptionPane.showMessageDialog(
                        view,
                        "Stock limit cannot be negative.");

                return;
            }

            List<Product> products =
                    dao.getLowStockProducts(limit);

            view.displayProducts(products);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please enter a valid number.");
        }
    }
}