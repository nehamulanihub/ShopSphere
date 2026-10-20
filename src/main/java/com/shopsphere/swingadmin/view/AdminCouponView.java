package com.shopsphere.swingadmin.view;

import com.shopsphere.model.Coupon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminCouponView extends JFrame {

    private JTable couponTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton refreshButton;

    public AdminCouponView() {

        setTitle("ShopSphere - Coupon Management");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{
                        "Coupon ID",
                        "Code",
                        "Discount",
                        "Expiry Date",
                        "Active"
                }, 0);

        couponTable = new JTable(tableModel);

        deleteButton = new JButton("Delete Coupon");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(couponTable),
                BorderLayout.CENTER);

        add(buttonPanel,
                BorderLayout.SOUTH);
    }

    public void displayCoupons(List<Coupon> coupons) {

        tableModel.setRowCount(0);

        for (Coupon coupon : coupons) {

            tableModel.addRow(new Object[]{
                    coupon.getCouponId(),
                    coupon.getCode(),
                    coupon.getDiscount(),
                    coupon.getExpiryDate(),
                    coupon.isActive()
            });
        }
    }

    public JTable getCouponTable() {
        return couponTable;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}