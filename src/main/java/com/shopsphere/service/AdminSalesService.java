package com.shopsphere.service;

import com.shopsphere.dao.AdminSalesDAO;

import java.math.BigDecimal;

public class AdminSalesService {

    private AdminSalesDAO salesDAO =
            new AdminSalesDAO();

    public int getTotalOrders() {
        return salesDAO.getTotalOrders();
    }

    public BigDecimal getTotalSales() {
        return salesDAO.getTotalSales();
    }

    public BigDecimal getAverageOrderValue() {
        return salesDAO.getAverageOrderValue();
    }
}