package com.shopsphere.service;

import com.shopsphere.dao.CategoryDAO;
import com.shopsphere.model.Category;

import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAO();

    public boolean addCategory(Category category) {

        if (category == null) {
            return false;
        }

        if (category.getName() == null ||
                category.getName().trim().isEmpty()) {
            return false;
        }

        category.setName(
                category.getName().trim());

        if (category.getDescription() != null) {
            category.setDescription(
                    category.getDescription().trim());
        }

        return categoryDAO.addCategory(category);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public Category getCategoryById(int categoryId) {

        if (categoryId <= 0) {
            return null;
        }

        return categoryDAO.getCategoryById(categoryId);
    }

    public boolean updateCategory(Category category) {

        if (category == null ||
                category.getCategoryId() <= 0) {
            return false;
        }

        if (category.getName() == null ||
                category.getName().trim().isEmpty()) {
            return false;
        }

        category.setName(
                category.getName().trim());

        if (category.getDescription() != null) {
            category.setDescription(
                    category.getDescription().trim());
        }

        return categoryDAO.updateCategory(category);
    }

    public boolean deleteCategory(int categoryId) {

        if (categoryId <= 0) {
            return false;
        }

        return categoryDAO.deleteCategory(categoryId);
    }
}