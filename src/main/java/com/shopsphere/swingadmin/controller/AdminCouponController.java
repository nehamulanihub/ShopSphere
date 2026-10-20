package com.shopsphere.swingadmin.controller;

import com.shopsphere.model.Coupon;
import com.shopsphere.swingadmin.dao.AdminCouponDAO;
import com.shopsphere.swingadmin.view.AdminCouponView;

import javax.swing.*;
import java.util.List;

public class AdminCouponController {

    private AdminCouponView view;
    private AdminCouponDAO dao;

    public AdminCouponController(
            AdminCouponView view,
            AdminCouponDAO dao) {

        this.view = view;
        this.dao = dao;

        loadCoupons();

        view.getRefreshButton()
                .addActionListener(e -> loadCoupons());

        view.getDeleteButton()
                .addActionListener(e -> deleteCoupon());
    }

    private void loadCoupons() {

        List<Coupon> coupons =
                dao.getAllCoupons();

        view.displayCoupons(coupons);
    }

    private void deleteCoupon() {

        int selectedRow =
                view.getCouponTable().getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please select a coupon first.");

            return;
        }

        int couponId =
                (int) view.getCouponTable()
                        .getValueAt(selectedRow, 0);

        int choice =
                JOptionPane.showConfirmDialog(
                        view,
                        "Are you sure you want to delete this coupon?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {

            boolean deleted =
                    dao.deleteCoupon(couponId);

            if (deleted) {

                JOptionPane.showMessageDialog(
                        view,
                        "Coupon deleted successfully.");

                loadCoupons();

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "Coupon could not be deleted.");
            }
        }
    }
}