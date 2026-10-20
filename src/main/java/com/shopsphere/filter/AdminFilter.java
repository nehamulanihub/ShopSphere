package com.shopsphere.filter;

import com.shopsphere.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();

        // Allow admin login page without authentication
        if (requestURI.endsWith("/admin/login")
                || requestURI.endsWith("/admin/login.jsp")) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        User user = null;

        if (session != null) {
            user = (User) session.getAttribute("loggedInUser");
        }

        if (user == null ||
            !"ADMIN".equalsIgnoreCase(user.getRole())) {

            res.sendRedirect(req.getContextPath() + "/admin/login");
            return;
        }

        chain.doFilter(request, response);
    }
}