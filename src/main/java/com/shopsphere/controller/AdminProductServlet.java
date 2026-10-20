package com.shopsphere.controller;

import com.shopsphere.model.Product;
import com.shopsphere.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin/products")
public class AdminProductServlet extends HttpServlet {

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
                "/admin/products.jsp")
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

                Product product = new Product();

                product.setName(
                        request.getParameter("name"));

                product.setDescription(
                        request.getParameter("description"));

                product.setImageUrl(
                        request.getParameter("imageUrl"));

                product.setCategoryId(
                        Integer.parseInt(
                                request.getParameter(
                                        "categoryId")));

                product.setPrice(
                        new BigDecimal(
                                request.getParameter(
                                        "price")));

                product.setStock(
                        Integer.parseInt(
                                request.getParameter(
                                        "stock")));

                productService.addProduct(product);

            } else if ("update".equals(action)) {

                Product product = new Product();

                product.setProductId(
                        Integer.parseInt(
                                request.getParameter(
                                        "productId")));

                product.setName(
                        request.getParameter("name"));

                product.setDescription(
                        request.getParameter("description"));

                product.setImageUrl(
                        request.getParameter("imageUrl"));

                product.setCategoryId(
                        Integer.parseInt(
                                request.getParameter(
                                        "categoryId")));

                product.setPrice(
                        new BigDecimal(
                                request.getParameter(
                                        "price")));

                product.setStock(
                        Integer.parseInt(
                                request.getParameter(
                                        "stock")));

                productService.updateProduct(product);

            } else if ("delete".equals(action)) {

                int productId =
                        Integer.parseInt(
                                request.getParameter(
                                        "productId"));

                productService.deleteProduct(
                        productId);
            }

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/products");
    }
}