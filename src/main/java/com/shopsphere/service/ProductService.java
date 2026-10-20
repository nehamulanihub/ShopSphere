package com.shopsphere.service;

import com.shopsphere.dao.ProductDAO;
import com.shopsphere.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    public boolean addProduct(Product product) {

        if (product == null) {
            return false;
        }

        if (product.getName() == null ||
                product.getName().trim().isEmpty()) {
            return false;
        }

        if (product.getCategoryId() <= 0) {
            return false;
        }

        if (product.getPrice() == null ||
                product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (product.getStock() < 0) {
            return false;
        }

        product.setName(product.getName().trim());

        if (product.getDescription() != null) {
            product.setDescription(
                    product.getDescription().trim());
        }

        if (product.getImageUrl() != null) {
            product.setImageUrl(
                    product.getImageUrl().trim());
        }

        return productDAO.addProduct(product);
    }

    public Product getProductById(int productId) {
        if (productId <= 0) {
            return null;
        }

        return productDAO.getProductById(productId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public List<Product> searchProducts(String keyword) {
        return productDAO.searchProducts(keyword);
    }

    public boolean updateProduct(Product product) {

        if (product == null ||
                product.getProductId() <= 0) {
            return false;
        }

        if (product.getName() == null ||
                product.getName().trim().isEmpty()) {
            return false;
        }

        if (product.getCategoryId() <= 0) {
            return false;
        }

        if (product.getPrice() == null ||
                product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (product.getStock() < 0) {
            return false;
        }

        product.setName(product.getName().trim());

        if (product.getDescription() != null) {
            product.setDescription(
                    product.getDescription().trim());
        }

        if (product.getImageUrl() != null) {
            product.setImageUrl(
                    product.getImageUrl().trim());
        }

        return productDAO.updateProduct(product);
    }

    public List<Product> filterAndSortProducts(
            String keyword,
            int categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String sort) {

        return productDAO.filterAndSortProducts(
                keyword,
                categoryId,
                minPrice,
                maxPrice,
                sort);
    }

    public boolean deleteProduct(int productId) {

        if (productId <= 0) {
            return false;
        }

        return productDAO.deleteProduct(productId);
    }
}