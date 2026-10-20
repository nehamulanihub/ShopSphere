package com.shopsphere.swingadmin.controller;

import com.shopsphere.model.User;
import com.shopsphere.swingadmin.dao.AdminCustomerDAO;
import com.shopsphere.swingadmin.view.AdminCustomerView;

import javax.swing.*;
import java.util.List;

public class AdminCustomerController {

    private AdminCustomerView view;
    private AdminCustomerDAO dao;

    public AdminCustomerController(
            AdminCustomerView view,
            AdminCustomerDAO dao) {

        this.view = view;
        this.dao = dao;

        loadCustomers();

        view.getRefreshButton()
                .addActionListener(e -> loadCustomers());

        view.getDeleteButton()
                .addActionListener(e -> deleteCustomer());
    }

    private void loadCustomers() {

        List<User> customers =
                dao.getAllCustomers();

        view.displayCustomers(customers);
    }

    private void deleteCustomer() {

        int selectedRow =
                view.getCustomerTable().getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please select a customer first.");

            return;
        }

        int userId =
                (int) view.getCustomerTable()
                        .getValueAt(selectedRow, 0);

        int choice =
                JOptionPane.showConfirmDialog(
                        view,
                        "Are you sure you want to delete this customer?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {

            boolean deleted =
                    dao.deleteCustomer(userId);

            if (deleted) {

                JOptionPane.showMessageDialog(
                        view,
                        "Customer deleted successfully.");

                loadCustomers();

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "Customer could not be deleted.");
            }
        }
    }
}