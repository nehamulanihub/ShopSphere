package com.shopsphere.controller;

import com.shopsphere.model.Category;
import com.shopsphere.service.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/categories")
public class AdminCategoryServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "categories",
                categoryService.getAllCategories());

        request.getRequestDispatcher(
                "/admin/categories.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        try {

            if ("add".equals(action)) {

                Category category = new Category();

                category.setName(
                        request.getParameter("name"));

                category.setDescription(
                        request.getParameter(
                                "description"));

                categoryService.addCategory(category);

            } else if ("update".equals(action)) {

                Category category = new Category();

                category.setCategoryId(
                        Integer.parseInt(
                                request.getParameter(
                                        "categoryId")));

                category.setName(
                        request.getParameter("name"));

                category.setDescription(
                        request.getParameter(
                                "description"));

                categoryService.updateCategory(
                        category);

            } else if ("delete".equals(action)) {

                int categoryId =
                        Integer.parseInt(
                                request.getParameter(
                                        "categoryId"));

                categoryService.deleteCategory(
                        categoryId);
            }

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/categories");
    }
}