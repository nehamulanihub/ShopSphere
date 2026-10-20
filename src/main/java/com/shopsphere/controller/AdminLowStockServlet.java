package com.shopsphere.controller;

import com.shopsphere.model.Product;
import com.shopsphere.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/low-stock")
public class AdminLowStockServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() {
        productService = new ProductService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products =
                productService.getAllProducts();

        List<Product> lowStockProducts =
                new ArrayList<>();

        for (Product product : products) {

            if (product.getStock() <= 5) {
                lowStockProducts.add(product);
            }
        }

        request.setAttribute(
                "products",
                lowStockProducts);

        request.getRequestDispatcher(
                "/admin/low-stock.jsp")
                .forward(request, response);
    }
}