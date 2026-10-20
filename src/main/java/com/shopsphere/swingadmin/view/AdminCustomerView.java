package com.shopsphere.swingadmin.view;

import com.shopsphere.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminCustomerView extends JFrame {

    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton refreshButton;

    public AdminCustomerView() {

        setTitle("ShopSphere - Customer Management");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{
                        "User ID",
                        "Name",
                        "Email",
                        "Phone",
                        "Role"
                }, 0);

        customerTable = new JTable(tableModel);

        deleteButton = new JButton("Delete Customer");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(customerTable),
                BorderLayout.CENTER);

        add(buttonPanel,
                BorderLayout.SOUTH);
    }

    public void displayCustomers(List<User> customers) {

        tableModel.setRowCount(0);

        for (User customer : customers) {

            tableModel.addRow(new Object[]{
                    customer.getUserId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getRole()
            });
        }
    }

    public JTable getCustomerTable() {
        return customerTable;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}