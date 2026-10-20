package com.shopsphere.controller;

import com.shopsphere.model.Review;
import com.shopsphere.model.User;
import com.shopsphere.service.ReviewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    private ReviewService reviewService;

    @Override
    public void init() {
        reviewService = new ReviewService();
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
                (User) session.getAttribute(
                        "loggedInUser");

        try {

            int productId =
                    Integer.parseInt(
                            request.getParameter(
                                    "productId"));

            int rating =
                    Integer.parseInt(
                            request.getParameter(
                                    "rating"));

            String comment =
                    request.getParameter("comment");

            if (rating < 1 || rating > 5 ||
                    comment == null ||
                    comment.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/products?id="
                                + productId);

                return;
            }

            Review review = new Review();

            review.setUserId(
                    user.getUserId());

            review.setProductId(
                    productId);

            review.setRating(
                    rating);

            review.setComment(
                    comment.trim());

            reviewService.addReview(review);

            response.sendRedirect(
                    request.getContextPath()
                            + "/products?id="
                            + productId);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/products");
        }
    }
}