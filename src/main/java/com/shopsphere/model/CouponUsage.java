package com.shopsphere.model;

import java.time.LocalDateTime;

public class CouponUsage {

    private int usageId;
    private int couponId;
    private int userId;
    private int orderId;
    private LocalDateTime usedAt;

    public CouponUsage() {
    }

    public CouponUsage(int usageId, int couponId, int userId,
                       int orderId, LocalDateTime usedAt) {
        this.usageId = usageId;
        this.couponId = couponId;
        this.userId = userId;
        this.orderId = orderId;
        this.usedAt = usedAt;
    }

    public int getUsageId() {
        return usageId;
    }

    public void setUsageId(int usageId) {
        this.usageId = usageId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(LocalDateTime usedAt) {
        this.usedAt = usedAt;
    }
}