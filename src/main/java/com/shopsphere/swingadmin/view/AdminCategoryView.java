package com.shopsphere.swingadmin.view;

import com.shopsphere.model.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminCategoryView extends JFrame {

    private JTable categoryTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton refreshButton;

    public AdminCategoryView() {

        setTitle("ShopSphere - Category Management");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new String[]{
                        "Category ID",
                        "Name",
                        "Description"
                }, 0);

        categoryTable = new JTable(tableModel);

        deleteButton = new JButton("Delete Category");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(categoryTable),
                BorderLayout.CENTER);

        add(buttonPanel,
                BorderLayout.SOUTH);
    }

    public void displayCategories(List<Category> categories) {

        tableModel.setRowCount(0);

        for (Category category : categories) {

            tableModel.addRow(new Object[]{
                    category.getCategoryId(),
                    category.getName(),
                    category.getDescription()
            });
        }
    }

    public JTable getCategoryTable() {
        return categoryTable;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}