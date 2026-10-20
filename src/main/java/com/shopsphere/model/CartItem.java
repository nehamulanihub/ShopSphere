package com.shopsphere.model;

import java.math.BigDecimal;

public class CartItem {

    private int cartId;
    private int userId;
    private int productId;
    private int quantity;
    private BigDecimal price;
private String productName;

    public CartItem() {
    }

    public CartItem(int cartId, int userId, int productId,
                    int quantity, BigDecimal price) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
public String getProductName() {
    return productName;
}

public void setProductName(String productName) {
    this.productName = productName;
}
}