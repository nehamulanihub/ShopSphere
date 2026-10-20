package com.shopsphere.controller;

import com.shopsphere.dao.OrderDAO;
import com.shopsphere.dao.ProductDAO;
import com.shopsphere.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.shopsphere.model.Order;
import com.shopsphere.model.Product;
import com.shopsphere.model.User;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private ProductDAO productDAO;
    private UserDAO userDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() {

        productDAO = new ProductDAO();
        userDAO = new UserDAO();
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products =
                productDAO.getAllProducts();

        List<User> users =
                userDAO.getAllUsers();

        List<Order> orders =
                orderDAO.getAllOrders();

        BigDecimal totalSales =
                BigDecimal.ZERO;

        for (Order order : orders) {

            if (order.getTotalAmount() != null &&
                    !"CANCELLED".equalsIgnoreCase(
                            order.getStatus())) {

                totalSales =
                        totalSales.add(
                                order.getTotalAmount());
            }
        }

        request.setAttribute(
                "totalProducts",
                products.size());

        request.setAttribute(
                "totalUsers",
                users.size());

        request.setAttribute(
                "totalOrders",
                orders.size());

        request.setAttribute(
                "totalSales",
                totalSales);

        request.getRequestDispatcher(
                "/admin/dashboard.jsp")
                .forward(request, response);
    }
}