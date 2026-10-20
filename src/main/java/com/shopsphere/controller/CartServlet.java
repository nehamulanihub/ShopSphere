package com.shopsphere.controller;

import com.shopsphere.model.CartItem;
import com.shopsphere.model.User;
import com.shopsphere.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init() {
        cartService = new CartService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");
            return;
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        request.setAttribute(
                "cartItems",
                cartService.getCartByUserId(
                        user.getUserId()));

        request.getRequestDispatcher(
                "/cart.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");
            return;
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        String action =
                request.getParameter("action");

        try {

            if ("add".equals(action)) {

                int productId =
                        Integer.parseInt(
                                request.getParameter(
                                        "productId"));

                int quantity =
                        Integer.parseInt(
                                request.getParameter(
                                        "quantity"));

                CartItem item = new CartItem();

                item.setUserId(user.getUserId());
                item.setProductId(productId);
                item.setQuantity(quantity);

                cartService.addToCart(item);

            } else if ("update".equals(action)) {

                int cartId =
                        Integer.parseInt(
                                request.getParameter(
                                        "cartId"));

                int quantity =
                        Integer.parseInt(
                                request.getParameter(
                                        "quantity"));

                cartService.updateQuantity(
                        cartId,
                        user.getUserId(),
                        quantity);

            } else if ("remove".equals(action)) {

                int cartId =
                        Integer.parseInt(
                                request.getParameter(
                                        "cartId"));

                cartService.removeFromCart(
                        cartId,
                        user.getUserId());
            }

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }
}