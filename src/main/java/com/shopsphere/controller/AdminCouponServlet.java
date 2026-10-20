package com.shopsphere.controller;

import com.shopsphere.model.Coupon;
import com.shopsphere.service.CouponService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/admin/coupons")
public class AdminCouponServlet extends HttpServlet {

    private CouponService couponService;

    @Override
    public void init() {
        couponService = new CouponService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Coupon> coupons =
                couponService.getAllCoupons();

        request.setAttribute(
                "coupons",
                coupons);

        request.getRequestDispatcher(
                "/admin/coupons.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        try {

            if ("add".equals(action)) {

                String code =
                        request.getParameter("code");

                String discount =
                        request.getParameter("discount");

                String expiryDate =
                        request.getParameter("expiryDate");

                String active =
                        request.getParameter("active");

                Coupon coupon =
                        new Coupon();

                coupon.setCode(
                        code != null
                                ? code.trim().toUpperCase()
                                : "");

                coupon.setDiscount(
                        new BigDecimal(discount));

                if (expiryDate != null &&
                        !expiryDate.trim().isEmpty()) {

                    coupon.setExpiryDate(
                            LocalDate.parse(
                                    expiryDate));
                }

                coupon.setActive(
                        "true".equalsIgnoreCase(active));

                couponService.addCoupon(coupon);
            }

            else if ("update".equals(action)) {

                int couponId =
                        Integer.parseInt(
                                request.getParameter(
                                        "couponId"));

                String code =
                        request.getParameter("code");

                String discount =
                        request.getParameter("discount");

                String expiryDate =
                        request.getParameter("expiryDate");

                String active =
                        request.getParameter("active");

                Coupon coupon =
                        new Coupon();

                coupon.setCouponId(couponId);

                coupon.setCode(
                        code != null
                                ? code.trim().toUpperCase()
                                : "");

                coupon.setDiscount(
                        new BigDecimal(discount));

                if (expiryDate != null &&
                        !expiryDate.trim().isEmpty()) {

                    coupon.setExpiryDate(
                            LocalDate.parse(
                                    expiryDate));
                }

                coupon.setActive(
                        "true".equalsIgnoreCase(active));

                couponService.updateCoupon(coupon);
            }

            else if ("delete".equals(action)) {

                int couponId =
                        Integer.parseInt(
                                request.getParameter(
                                        "couponId"));

                couponService.deleteCoupon(
                        couponId);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/coupons");
    }
}