package com.shopsphere.controller;

import com.shopsphere.service.AdminSalesService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/sales")
public class AdminSalesServlet extends HttpServlet {

    private AdminSalesService salesService;

    @Override
    public void init() {

        salesService =
                new AdminSalesService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "totalOrders",
                salesService.getTotalOrders());

        request.setAttribute(
                "totalSales",
                salesService.getTotalSales());

        request.setAttribute(
                "averageOrderValue",
                salesService.getAverageOrderValue());

        request.getRequestDispatcher(
                "/admin/sales.jsp")
                .forward(request, response);
    }
}