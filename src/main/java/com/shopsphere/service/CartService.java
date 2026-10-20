package com.shopsphere.service;

import com.shopsphere.dao.CartDAO;
import com.shopsphere.dao.ProductDAO;
import com.shopsphere.model.CartItem;
import com.shopsphere.model.Product;

import java.util.List;

public class CartService {

    private CartDAO cartDAO = new CartDAO();
    private ProductDAO productDAO = new ProductDAO();

    public boolean addToCart(CartItem item) {

        if (item == null ||
                item.getUserId() <= 0 ||
                item.getProductId() <= 0 ||
                item.getQuantity() <= 0) {

            return false;
        }

        Product product =
                productDAO.getProductById(
                        item.getProductId());

        if (product == null ||
                item.getQuantity() > product.getStock()) {

            return false;
        }

        return cartDAO.addToCart(item);
    }

    public List<CartItem> getCartByUserId(int userId) {
        return cartDAO.getCartByUserId(userId);
    }

    public boolean updateQuantity(
            int cartId,
            int userId,
            int quantity) {

        if (cartId <= 0 ||
                userId <= 0 ||
                quantity <= 0) {

            return false;
        }

        CartItem item = null;

        for (CartItem cartItem :
                cartDAO.getCartByUserId(userId)) {

            if (cartItem.getCartId() == cartId) {
                item = cartItem;
                break;
            }
        }

        if (item == null) {
            return false;
        }

        Product product =
                productDAO.getProductById(
                        item.getProductId());

        if (product == null ||
                quantity > product.getStock()) {

            return false;
        }

        return cartDAO.updateQuantity(
                cartId,
                userId,
                quantity);
    }

    public boolean removeFromCart(
            int cartId,
            int userId) {

        if (cartId <= 0 ||
                userId <= 0) {

            return false;
        }

        return cartDAO.removeFromCart(
                cartId,
                userId);
    }

    public boolean clearCart(int userId) {
        return cartDAO.clearCart(userId);
    }
}