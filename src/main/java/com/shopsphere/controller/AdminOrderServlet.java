package com.shopsphere.controller;

import com.shopsphere.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/orders")
public class AdminOrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() {
        orderService = new OrderService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "orders",
                orderService.getAllOrders());

        request.getRequestDispatcher(
                "/admin/orders.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        if ("updateStatus".equals(action)) {

            try {

                int orderId =
                        Integer.parseInt(
                                request.getParameter(
                                        "orderId"));

                String status =
                        request.getParameter("status");

                orderService.updateOrderStatus(
                        orderId,
                        status);

            } catch (NumberFormatException e) {

                e.printStackTrace();
            }
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/orders");
    }
}