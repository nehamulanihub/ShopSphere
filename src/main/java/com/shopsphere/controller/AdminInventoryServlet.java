package com.shopsphere.controller;

import com.shopsphere.model.Product;
import com.shopsphere.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/inventory")
public class AdminInventoryServlet extends HttpServlet {

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

        request.setAttribute(
                "products",
                productService.getAllProducts());

        request.getRequestDispatcher(
                "/admin/inventory.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int productId =
                    Integer.parseInt(
                            request.getParameter(
                                    "productId"));

            int stock =
                    Integer.parseInt(
                            request.getParameter(
                                    "stock"));

            Product product =
                    productService.getProductById(
                            productId);

            if (product != null &&
                    stock >= 0) {

                product.setStock(stock);

                productService.updateProduct(
                        product);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/inventory");
    }
}