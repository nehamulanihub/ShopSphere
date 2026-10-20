package com.shopsphere.controller;

import com.shopsphere.model.User;
import com.shopsphere.model.WishlistItem;
import com.shopsphere.service.WishlistService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {

    private WishlistService wishlistService;

    @Override
    public void init() {
        wishlistService = new WishlistService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");
            return;
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        request.setAttribute(
                "wishlistItems",
                wishlistService.getWishlistByUserId(
                        user.getUserId()
                )
        );

        request.getRequestDispatcher(
                "/wishlist.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

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

                WishlistItem item =
                        new WishlistItem();

                item.setUserId(
                        user.getUserId());

                item.setProductId(
                        productId);

                wishlistService.addToWishlist(item);

            } else if ("remove".equals(action)) {

                int wishlistId =
                        Integer.parseInt(
                                request.getParameter(
                                        "wishlistId"));

                wishlistService.removeFromWishlist(
                        wishlistId,
                        user.getUserId());
            }

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath() + "/wishlist");
    }
}