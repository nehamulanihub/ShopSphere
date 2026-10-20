package com.shopsphere.service;

import com.shopsphere.dao.WishlistDAO;
import com.shopsphere.model.WishlistItem;

import java.util.List;

public class WishlistService {

    private WishlistDAO wishlistDAO = new WishlistDAO();

    public boolean addToWishlist(WishlistItem item) {
        return wishlistDAO.addToWishlist(item);
    }

    public List<WishlistItem> getWishlistByUserId(int userId) {
        return wishlistDAO.getWishlistByUserId(userId);
    }

    public boolean removeFromWishlist(
            int wishlistId,
            int userId) {

        return wishlistDAO.removeFromWishlist(
                wishlistId,
                userId);
    }
}