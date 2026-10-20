package com.shopsphere.swingadmin.controller;

import com.shopsphere.model.Category;
import com.shopsphere.swingadmin.dao.AdminCategoryDAO;
import com.shopsphere.swingadmin.view.AdminCategoryView;

import javax.swing.*;
import java.util.List;

public class AdminCategoryController {

    private AdminCategoryView view;
    private AdminCategoryDAO dao;

    public AdminCategoryController(
            AdminCategoryView view,
            AdminCategoryDAO dao) {

        this.view = view;
        this.dao = dao;

        loadCategories();

        view.getRefreshButton()
                .addActionListener(e -> loadCategories());

        view.getDeleteButton()
                .addActionListener(e -> deleteCategory());
    }

    private void loadCategories() {

        List<Category> categories =
                dao.getAllCategories();

        view.displayCategories(categories);
    }

    private void deleteCategory() {

        int selectedRow =
                view.getCategoryTable().getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please select a category first.");

            return;
        }

        int categoryId =
                (int) view.getCategoryTable()
                        .getValueAt(selectedRow, 0);

        int choice =
                JOptionPane.showConfirmDialog(
                        view,
                        "Are you sure you want to delete this category?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {

            boolean deleted =
                    dao.deleteCategory(categoryId);

            if (deleted) {

                JOptionPane.showMessageDialog(
                        view,
                        "Category deleted successfully.");

                loadCategories();

            } else {

                JOptionPane.showMessageDialog(
                        view,
                        "Category could not be deleted.");
            }
        }
    }
}