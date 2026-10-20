package com.shopsphere.controller;

import com.shopsphere.model.User;
import com.shopsphere.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/admin/login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.loginUser(email, password);

        if (user != null && "ADMIN".equalsIgnoreCase(user.getRole())) {

            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);

            response.sendRedirect(request.getContextPath()
                    + "/admin/dashboard");

        } else {

            request.setAttribute("error",
                    "Invalid admin email or password");

            request.getRequestDispatcher("/admin/login.jsp")
                   .forward(request, response);
        }
    }
}