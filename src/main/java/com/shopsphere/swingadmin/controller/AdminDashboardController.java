package com.shopsphere.swingadmin.controller;

import com.shopsphere.swingadmin.dao.AdminCategoryDAO;
import com.shopsphere.swingadmin.dao.AdminCustomerDAO;
import com.shopsphere.swingadmin.dao.AdminInventoryDAO;
import com.shopsphere.swingadmin.dao.AdminOrderDAO;
import com.shopsphere.swingadmin.dao.AdminProductDAO;
import com.shopsphere.swingadmin.view.AdminCategoryView;
import com.shopsphere.swingadmin.view.AdminCustomerView;
import com.shopsphere.swingadmin.view.AdminDashboardView;
import com.shopsphere.swingadmin.view.AdminInventoryView;
import com.shopsphere.swingadmin.view.AdminOrderView;
import com.shopsphere.swingadmin.view.AdminProductView;

import com.shopsphere.swingadmin.dao.AdminCouponDAO;
import com.shopsphere.swingadmin.view.AdminCouponView;

import com.shopsphere.swingadmin.dao.AdminLowStockDAO;
import com.shopsphere.swingadmin.view.AdminLowStockView;

import com.shopsphere.swingadmin.dao.AdminSalesDAO;
import com.shopsphere.swingadmin.view.AdminSalesView;


public class AdminDashboardController {

    private AdminDashboardView view;

    public AdminDashboardController(
            AdminDashboardView view) {

        this.view = view;

        this.view.getProductButton()
                .addActionListener(e -> openProducts());

        this.view.getCustomerButton()
                .addActionListener(e -> openCustomers());

        this.view.getCategoryButton()
                .addActionListener(e -> openCategories());

        this.view.getInventoryButton()
                .addActionListener(e -> openInventory());

        this.view.getOrderButton()
                .addActionListener(e -> openOrders());

        this.view.getCouponButton()
        .addActionListener(e -> openCoupons());

        this.view.getLowStockButton()
        .addActionListener(e -> openLowStock());

        this.view.getExitButton()
                .addActionListener(e ->
                        System.exit(0));

        this.view.getSalesButton()
        .addActionListener(e -> openSales());
        
    }

    private void openProducts() {

        AdminProductView productView =
                new AdminProductView();

        new AdminProductController(
                productView,
                new AdminProductDAO()
        );

        productView.setVisible(true);
    }

    private void openCustomers() {

        AdminCustomerView customerView =
                new AdminCustomerView();

        new AdminCustomerController(
                customerView,
                new AdminCustomerDAO()
        );

        customerView.setVisible(true);
    }

    private void openCategories() {

        AdminCategoryView categoryView =
                new AdminCategoryView();

        new AdminCategoryController(
                categoryView,
                new AdminCategoryDAO()
        );

        categoryView.setVisible(true);
    }

    private void openInventory() {

        AdminInventoryView inventoryView =
                new AdminInventoryView();

        new AdminInventoryController(
                inventoryView,
                new AdminInventoryDAO()
        );

        inventoryView.setVisible(true);
    }

    private void openOrders() {

        AdminOrderView orderView =
                new AdminOrderView();

        new AdminOrderController(
                orderView,
                new AdminOrderDAO()
        );

        orderView.setVisible(true);
    }

    private void openLowStock() {

    AdminLowStockView lowStockView =
            new AdminLowStockView();

    new AdminLowStockController(
            lowStockView,
            new AdminLowStockDAO()
    );

    lowStockView.setVisible(true);
}

    private void openCoupons() {

    AdminCouponView couponView =
            new AdminCouponView();

    new AdminCouponController(
            couponView,
            new AdminCouponDAO()
    );

    couponView.setVisible(true);
}

private void openSales() {

    AdminSalesView salesView =
            new AdminSalesView();

    new AdminSalesController(
            salesView,
            new AdminSalesDAO()
    );

    salesView.setVisible(true);
}
}