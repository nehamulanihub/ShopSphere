package com.shopsphere.service;

import com.shopsphere.dao.CouponDAO;
import com.shopsphere.model.Coupon;

import java.math.BigDecimal;
import java.util.List;

public class CouponService {

    private final CouponDAO couponDAO =
            new CouponDAO();

    public Coupon getCouponByCode(String code) {

        if (code == null ||
                code.trim().isEmpty()) {

            return null;
        }

        return couponDAO.getCouponByCode(
                code.trim().toUpperCase());
    }

    public List<Coupon> getAllCoupons() {

        return couponDAO.getAllCoupons();
    }

    public boolean addCoupon(Coupon coupon) {

        if (coupon == null) {
            return false;
        }

        if (coupon.getCode() == null ||
                coupon.getCode().trim().isEmpty()) {

            return false;
        }

        if (coupon.getDiscount() == null ||
                coupon.getDiscount().compareTo(
                        BigDecimal.ZERO) < 0) {

            return false;
        }

        return couponDAO.addCoupon(coupon);
    }

    public boolean updateCoupon(Coupon coupon) {

        if (coupon == null) {
            return false;
        }

        if (coupon.getDiscount() == null ||
                coupon.getDiscount().compareTo(
                        BigDecimal.ZERO) < 0) {

            return false;
        }

        return couponDAO.updateCoupon(coupon);
    }

    public boolean deleteCoupon(int couponId) {

        if (couponId <= 0) {
            return false;
        }

        return couponDAO.deleteCoupon(couponId);
    }

    public BigDecimal applyCoupon(
            BigDecimal totalAmount,
            String couponCode) {

        if (totalAmount == null ||
                totalAmount.compareTo(
                        BigDecimal.ZERO) < 0) {

            return null;
        }

        Coupon coupon =
                getCouponByCode(couponCode);

        if (coupon == null) {
            return null;
        }

        // Check whether coupon is active
        if (!coupon.isActive()) {
            return null;
        }

        // Check expiry date
        if (coupon.getExpiryDate() != null &&
                coupon.getExpiryDate()
                        .isBefore(
                                java.time.LocalDate.now())) {

            return null;
        }

        BigDecimal discount =
                coupon.getDiscount();

        if (discount == null ||
                discount.compareTo(
                        BigDecimal.ZERO) < 0) {

            return null;
        }

        BigDecimal finalAmount =
                totalAmount.subtract(discount);

        // Final amount cannot be negative
        if (finalAmount.compareTo(
                BigDecimal.ZERO) < 0) {

            finalAmount =
                    BigDecimal.ZERO;
        }

        return finalAmount;
    }
}