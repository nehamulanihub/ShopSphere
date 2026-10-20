<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <c:choose>

        <c:when test="${empty product}">

            <div class="alert alert-danger">
                Product not found.
            </div>

            <a href="${pageContext.request.contextPath}/products"
               class="btn btn-primary">
                Back to Products
            </a>

        </c:when>

        <c:otherwise>

            <!-- Product Details -->

            <div class="row">

                <div class="col-md-6">

                    <div class="card shadow">

                        <div class="card-body text-center">

                            <h3>
                                ${product.name}
                            </h3>

                            <p class="text-muted">
                                Product ID: ${product.productId}
                            </p>

                            <h4 class="text-primary">
                                ₹ ${product.price}
                            </h4>

                        </div>

                    </div>

                </div>


                <div class="col-md-6">

                    <h2>
                        ${product.name}
                    </h2>

                    <p class="mt-3">
                        ${product.description}
                    </p>

                    <p>
                        <strong>Category ID:</strong>
                        ${product.categoryId}
                    </p>

                    <p>
                        <strong>Available Stock:</strong>
                        ${product.stock}
                    </p>


                    <!-- Add To Cart -->

                    <form action="${pageContext.request.contextPath}/cart"
                          method="post">

                        <input type="hidden"
                               name="action"
                               value="add">

                        <input type="hidden"
                               name="productId"
                               value="${product.productId}">

                        <div class="mb-3">

                            <label class="form-label">
                                Quantity
                            </label>

                            <input type="number"
                                   name="quantity"
                                   value="1"
                                   min="1"
                                   max="${product.stock}"
                                   class="form-control"
                                   style="max-width: 150px;"
                                   required>

                        </div>

                        <button type="submit"
                                class="btn btn-primary">

                            Add to Cart

                        </button>

                    </form>


                    <!-- Wishlist -->

                    <form action="${pageContext.request.contextPath}/wishlist"
                          method="post"
                          class="mt-2">

                        <input type="hidden"
                               name="action"
                               value="add">

                        <input type="hidden"
                               name="productId"
                               value="${product.productId}">

                        <button type="submit"
                                class="btn btn-outline-danger">

                            Add to Wishlist

                        </button>

                    </form>


                    <a href="${pageContext.request.contextPath}/products"
                       class="btn btn-secondary mt-3">

                        Back to Products

                    </a>

                </div>

            </div>


            <!-- Reviews Section -->

            <hr class="my-5">

            <h3 class="mb-4">
                Customer Reviews
            </h3>


            <c:choose>

                <c:when test="${empty reviews}">

                    <div class="alert alert-info">

                        No reviews yet.
                        Be the first to review this product!

                    </div>

                </c:when>


                <c:otherwise>

                    <c:forEach var="review"
                               items="${reviews}">

                        <div class="card shadow-sm mb-3">

                            <div class="card-body">

                                <h5 class="card-title">

                                    Rating:

                                    <span class="text-warning">

                                        ${review.rating} / 5 ⭐

                                    </span>

                                </h5>

                                <p class="card-text">

                                    ${review.comment}

                                </p>

                                <small class="text-muted">

                                    User ID:
                                    ${review.userId}

                                </small>

                            </div>

                        </div>

                    </c:forEach>

                </c:otherwise>

            </c:choose>


            <!-- Write Review -->

            <div class="card shadow mt-5 mb-5">

                <div class="card-body">

                    <h4 class="mb-4">
                        Write a Review
                    </h4>


                    <form action="${pageContext.request.contextPath}/review"
                          method="post">

                        <input type="hidden"
                               name="productId"
                               value="${product.productId}">


                        <div class="mb-3">

                            <label class="form-label">
                                Rating
                            </label>

                            <select name="rating"
                                    class="form-select"
                                    required>

                                <option value="">
                                    Select Rating
                                </option>

                                <option value="5">
                                    5 - Excellent ⭐⭐⭐⭐⭐
                                </option>

                                <option value="4">
                                    4 - Very Good ⭐⭐⭐⭐
                                </option>

                                <option value="3">
                                    3 - Good ⭐⭐⭐
                                </option>

                                <option value="2">
                                    2 - Average ⭐⭐
                                </option>

                                <option value="1">
                                    1 - Poor ⭐
                                </option>

                            </select>

                        </div>


                        <div class="mb-3">

                            <label class="form-label">
                                Your Review
                            </label>

                            <textarea name="comment"
                                      class="form-control"
                                      rows="4"
                                      placeholder="Write your review here..."
                                      required></textarea>

                        </div>


                        <button type="submit"
                                class="btn btn-warning">

                            Submit Review

                        </button>

                    </form>

                </div>

            </div>

        </c:otherwise>

    </c:choose>

</div>

<%@ include file="fragments/footer.jspf" %>