package com.shopsphere.controller;

import com.shopsphere.model.Category;
import com.shopsphere.model.Product;
import com.shopsphere.model.Review;
import com.shopsphere.service.CategoryService;
import com.shopsphere.service.ProductService;
import com.shopsphere.service.ReviewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService;
    private CategoryService categoryService;
    private ReviewService reviewService;

    @Override
    public void init() {

        productService =
                new ProductService();

        categoryService =
                new CategoryService();

        reviewService =
                new ReviewService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String productIdParameter =
                request.getParameter("id");


        // ---------------------------------------
        // PRODUCT DETAILS
        // ---------------------------------------

        if (productIdParameter != null &&
                !productIdParameter.trim().isEmpty()) {

            try {

                int productId =
                        Integer.parseInt(
                                productIdParameter);

                if (productId <= 0) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/products");

                    return;
                }


                Product product =
                        productService.getProductById(
                                productId);


                if (product == null) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/products");

                    return;
                }


                List<Review> reviews =
                        reviewService
                                .getReviewsByProductId(
                                        productId);


                request.setAttribute(
                        "product",
                        product);

                request.setAttribute(
                        "reviews",
                        reviews);


                request.getRequestDispatcher(
                        "/product-details.jsp")
                        .forward(
                                request,
                                response);

                return;

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/products");

                return;
            }
        }


        // ---------------------------------------
        // SEARCH / FILTER / SORT
        // ---------------------------------------

        String keyword =
                request.getParameter("search");

        String categoryParameter =
                request.getParameter("categoryId");

        String minPriceParameter =
                request.getParameter("minPrice");

        String maxPriceParameter =
                request.getParameter("maxPrice");

        String sort =
                request.getParameter("sort");


        int categoryId = 0;

        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;


        try {

            if (categoryParameter != null &&
                    !categoryParameter.trim().isEmpty()) {

                categoryId =
                        Integer.parseInt(
                                categoryParameter);

                if (categoryId < 0) {
                    categoryId = 0;
                }
            }


            if (minPriceParameter != null &&
                    !minPriceParameter.trim().isEmpty()) {

                minPrice =
                        new BigDecimal(
                                minPriceParameter);

                if (minPrice.compareTo(
                        BigDecimal.ZERO) < 0) {

                    minPrice = null;
                }
            }


            if (maxPriceParameter != null &&
                    !maxPriceParameter.trim().isEmpty()) {

                maxPrice =
                        new BigDecimal(
                                maxPriceParameter);

                if (maxPrice.compareTo(
                        BigDecimal.ZERO) < 0) {

                    maxPrice = null;
                }
            }


        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Please enter valid filter values.");

            categoryId = 0;
            minPrice = null;
            maxPrice = null;
        }


        // ---------------------------------------
        // LOAD CATEGORIES
        // ---------------------------------------

        List<Category> categories =
                categoryService.getAllCategories();

        request.setAttribute(
                "categories",
                categories);


        // ---------------------------------------
        // LOAD PRODUCTS
        // ---------------------------------------

        List<Product> products =
                productService.filterAndSortProducts(
                        keyword,
                        categoryId,
                        minPrice,
                        maxPrice,
                        sort);


        request.setAttribute(
                "products",
                products);


        // Keep filter values
        request.setAttribute(
                "search",
                keyword);

        request.setAttribute(
                "categoryId",
                categoryId);

        request.setAttribute(
                "minPrice",
                minPrice);

        request.setAttribute(
                "maxPrice",
                maxPrice);

        request.setAttribute(
                "sort",
                sort);


        request.getRequestDispatcher(
                "/products.jsp")
                .forward(
                        request,
                        response);
    }
}