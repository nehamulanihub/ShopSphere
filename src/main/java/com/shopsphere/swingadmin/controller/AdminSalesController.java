package com.shopsphere.swingadmin.controller;

import com.shopsphere.swingadmin.dao.AdminSalesDAO;
import com.shopsphere.swingadmin.view.AdminSalesView;

public class AdminSalesController {

    private AdminSalesView view;
    private AdminSalesDAO dao;

    public AdminSalesController(
            AdminSalesView view,
            AdminSalesDAO dao) {

        this.view = view;
        this.dao = dao;

        loadSalesSummary();

        view.getRefreshButton()
                .addActionListener(
                        e -> loadSalesSummary());
    }

    private void loadSalesSummary() {

        int totalOrders =
                dao.getTotalOrders();

        double totalSales =
                dao.getTotalSales();

        int deliveredOrders =
                dao.getDeliveredOrders();

        view.displaySummary(
                totalOrders,
                totalSales,
                deliveredOrders);
    }
}