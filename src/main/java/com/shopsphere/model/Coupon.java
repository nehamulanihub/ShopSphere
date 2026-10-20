package com.shopsphere.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Coupon {

    private int couponId;
    private String code;
    private BigDecimal discount;
    private LocalDate expiryDate;
    private boolean active;

    public Coupon() {
    }

    public Coupon(int couponId, String code, BigDecimal discount,
                  LocalDate expiryDate, boolean active) {
        this.couponId = couponId;
        this.code = code;
        this.discount = discount;
        this.expiryDate = expiryDate;
        this.active = active;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}