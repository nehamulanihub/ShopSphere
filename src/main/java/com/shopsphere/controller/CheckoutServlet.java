package com.shopsphere.controller;

import com.shopsphere.model.User;
import com.shopsphere.service.AddressService;
import com.shopsphere.service.CheckoutService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private CheckoutService checkoutService;
    private AddressService addressService;

    @Override
    public void init() {

        checkoutService = new CheckoutService();
        addressService = new AddressService();
    }

    // ==============================
    // SHOW CHECKOUT PAGE
    // ==============================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Check login
        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        // Load user's saved addresses
        request.setAttribute(
                "addresses",
                addressService.getAddressesByUserId(
                        user.getUserId()));

        // Open checkout page
        request.getRequestDispatcher(
                "/checkout.jsp")
                .forward(request, response);
    }

    // ==============================
    // PROCESS CHECKOUT
    // ==============================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Check login
        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        // ==============================
        // GET ADDRESS
        // ==============================

        String addressIdParameter =
                request.getParameter("addressId");

        int addressId;

        try {

            if (addressIdParameter == null ||
                    addressIdParameter.trim().isEmpty()) {

                throw new NumberFormatException();
            }

            addressId =
                    Integer.parseInt(
                            addressIdParameter);

            if (addressId <= 0) {

                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Please select a valid delivery address.");

            loadAddresses(
                    request,
                    user.getUserId());

            request.getRequestDispatcher(
                    "/checkout.jsp")
                    .forward(request, response);

            return;
        }

        // ==============================
        // GET PAYMENT METHOD
        // ==============================

        String paymentMethod =
                request.getParameter(
                        "paymentMethod");

        if (paymentMethod == null ||
                paymentMethod.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Please select a payment method.");

            loadAddresses(
                    request,
                    user.getUserId());

            request.getRequestDispatcher(
                    "/checkout.jsp")
                    .forward(request, response);

            return;
        }

        paymentMethod =
                paymentMethod.trim()
                        .toUpperCase();

        // ==============================
        // VALIDATE PAYMENT METHOD
        // ==============================

        if (!"COD".equals(paymentMethod) &&
                !"CARD".equals(paymentMethod) &&
                !"UPI".equals(paymentMethod)) {

            request.setAttribute(
                    "error",
                    "Invalid payment method.");

            loadAddresses(
                    request,
                    user.getUserId());

            request.getRequestDispatcher(
                    "/checkout.jsp")
                    .forward(request, response);

            return;
        }

        // ==============================
        // GET COUPON
        // ==============================

        String couponCode =
                request.getParameter(
                        "couponCode");

        if (couponCode != null) {

            couponCode =
                    couponCode.trim();

            if (couponCode.isEmpty()) {
                couponCode = null;
            }
        }

        // ==============================
        // PROCESS CHECKOUT
        // ==============================

        int orderId;

        try {

            orderId =
                    checkoutService.checkout(
                            user.getUserId(),
                            addressId,
                            paymentMethod,
                            couponCode);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    "Checkout failed. Please try again.");

            loadAddresses(
                    request,
                    user.getUserId());

            request.getRequestDispatcher(
                    "/checkout.jsp")
                    .forward(request, response);

            return;
        }

        // ==============================
        // CHECK RESULT
        // ==============================

        if (orderId > 0) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/order-success.jsp?orderId="
                            + orderId);

            return;
        }

        // ==============================
        // CHECKOUT FAILED
        // ==============================

        request.setAttribute(
                "error",
                "Checkout failed. Please check your cart, stock, or coupon.");

        loadAddresses(
                request,
                user.getUserId());

        request.getRequestDispatcher(
                "/checkout.jsp")
                .forward(request, response);
    }

    // ==============================
    // LOAD USER ADDRESSES
    // ==============================

    private void loadAddresses(
            HttpServletRequest request,
            int userId) {

        request.setAttribute(
                "addresses",
                addressService.getAddressesByUserId(
                        userId));
    }
}