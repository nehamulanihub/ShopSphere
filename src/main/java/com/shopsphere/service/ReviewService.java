package com.shopsphere.service;

import com.shopsphere.dao.ReviewDAO;
import com.shopsphere.dao.ProductDAO;
import com.shopsphere.model.Review;
import com.shopsphere.model.Product;

import java.util.List;

public class ReviewService {

    private ReviewDAO reviewDAO = new ReviewDAO();
    private ProductDAO productDAO = new ProductDAO();

    public boolean addReview(Review review) {

        if (review == null ||
                review.getUserId() <= 0 ||
                review.getProductId() <= 0) {

            return false;
        }

        if (review.getRating() < 1 ||
                review.getRating() > 5) {

            return false;
        }

        if (review.getComment() == null ||
                review.getComment().trim().isEmpty()) {

            return false;
        }

        Product product =
                productDAO.getProductById(
                        review.getProductId());

        if (product == null) {
            return false;
        }

        review.setComment(
                review.getComment().trim());

        return reviewDAO.addReview(review);
    }

    public List<Review> getReviewsByProductId(
            int productId) {

        return reviewDAO.getReviewsByProductId(
                productId);
    }
}