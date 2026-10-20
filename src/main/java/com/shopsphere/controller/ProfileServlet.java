package com.shopsphere.controller;

import com.shopsphere.model.User;
import com.shopsphere.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
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
                (User) session.getAttribute(
                        "loggedInUser");

        request.setAttribute("user", user);

        request.getRequestDispatcher(
                "/profile.jsp")
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
                (User) session.getAttribute(
                        "loggedInUser");

        String action =
                request.getParameter("action");

        // Change Password
        if ("changePassword".equals(action)) {

            String currentPassword =
                    request.getParameter("currentPassword");

            String newPassword =
                    request.getParameter("newPassword");

            String confirmPassword =
                    request.getParameter("confirmPassword");

            if (currentPassword == null ||
                    newPassword == null ||
                    confirmPassword == null ||
                    currentPassword.trim().isEmpty() ||
                    newPassword.trim().isEmpty() ||
                    confirmPassword.trim().isEmpty()) {

                request.setAttribute(
                        "passwordError",
                        "All password fields are required.");

            } else if (!newPassword.equals(confirmPassword)) {

                request.setAttribute(
                        "passwordError",
                        "New password and confirm password do not match.");

            } else if (newPassword.length() < 6) {

                request.setAttribute(
                        "passwordError",
                        "New password must contain at least 6 characters.");

            } else {

                boolean changed =
                        userService.changePassword(
                                user.getUserId(),
                                currentPassword,
                                newPassword);

                if (changed) {

                    request.setAttribute(
                            "passwordSuccess",
                            "Password changed successfully.");

                } else {

                    request.setAttribute(
                            "passwordError",
                            "Current password is incorrect.");
                }
            }

            request.setAttribute("user", user);

            request.getRequestDispatcher(
                    "/profile.jsp")
                    .forward(request, response);

            return;
        }

        // Update Profile
        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String phone =
                request.getParameter("phone");

        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);

        boolean updated =
                userService.updateUser(user);

        if (updated) {

            session.setAttribute(
                    "loggedInUser",
                    user);

            request.setAttribute(
                    "success",
                    "Profile updated successfully.");

        } else {

            request.setAttribute(
                    "error",
                    "Profile update failed.");
        }

        request.setAttribute(
                "user",
                user);

        request.getRequestDispatcher(
                "/profile.jsp")
                .forward(request, response);
    }
}