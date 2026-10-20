package com.shopsphere.controller;

import com.shopsphere.model.Order;
import com.shopsphere.model.OrderItem;
import com.shopsphere.model.User;
import com.shopsphere.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() {

        orderService =
                new OrderService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute(
                        "loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login");

            return;
        }


        User user =
                (User) session.getAttribute(
                        "loggedInUser");


        String orderIdParameter =
                request.getParameter("id");


        // ---------------------------------------
        // ORDER DETAILS
        // ---------------------------------------

        if (orderIdParameter != null &&
                !orderIdParameter.trim().isEmpty()) {

            try {

                int orderId =
                        Integer.parseInt(
                                orderIdParameter);


                if (orderId <= 0) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/orders");

                    return;
                }


                Order order =
                        orderService.getOrderById(
                                orderId);


                if (order == null ||
                        order.getUserId()
                                != user.getUserId()) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/orders");

                    return;
                }


                List<OrderItem> orderItems =
                        orderService.getOrderItems(
                                orderId);


                request.setAttribute(
                        "order",
                        order);

                request.setAttribute(
                        "orderItems",
                        orderItems);


                request.getRequestDispatcher(
                        "/order-details.jsp")
                        .forward(
                                request,
                                response);

                return;


            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/orders");

                return;
            }
        }


        // ---------------------------------------
        // ORDER HISTORY
        // ---------------------------------------

        List<Order> orders =
                orderService.getOrdersByUserId(
                        user.getUserId());


        request.setAttribute(
                "orders",
                orders);


        request.getRequestDispatcher(
                "/orders.jsp")
                .forward(
                        request,
                        response);
    }
}