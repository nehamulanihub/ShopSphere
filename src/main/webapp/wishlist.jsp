<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <h2 class="mb-4">My Wishlist</h2>

    <c:choose>

        <c:when test="${empty wishlistItems}">

            <div class="alert alert-info">
                Your wishlist is empty.
            </div>

            <a href="${pageContext.request.contextPath}/products"
               class="btn btn-primary">
                Browse Products
            </a>

        </c:when>

        <c:otherwise>

            <div class="row">

                <c:forEach var="item" items="${wishlistItems}">

                    <div class="col-md-4 mb-4">

                        <div class="card shadow h-100">

                            <div class="card-body">

                                <h5 class="card-title">
                                    Product ID: ${item.productId}
                                </h5>

                                <p class="card-text">
                                    Saved in your wishlist.
                                </p>

                                <!-- View Product -->

                                <a href="${pageContext.request.contextPath}/products?id=${item.productId}"
                                   class="btn btn-primary">
                                    View Product
                                </a>

                                <!-- Add to Cart -->

                                <form action="${pageContext.request.contextPath}/cart"
                                      method="post"
                                      class="d-inline">

                                    <input type="hidden"
                                           name="action"
                                           value="add">

                                    <input type="hidden"
                                           name="productId"
                                           value="${item.productId}">

                                    <input type="hidden"
                                           name="quantity"
                                           value="1">

                                    <button type="submit"
                                            class="btn btn-success">
                                        Add to Cart
                                    </button>

                                </form>

                                <!-- Remove from Wishlist -->

                                <form action="${pageContext.request.contextPath}/wishlist"
                                      method="post"
                                      class="mt-2">

                                    <input type="hidden"
                                           name="action"
                                           value="remove">

                                    <input type="hidden"
                                           name="wishlistId"
                                           value="${item.wishlistId}">

                                    <button type="submit"
                                            class="btn btn-danger">
                                        Remove
                                    </button>

                                </form>

                            </div>

                        </div>

                    </div>

                </c:forEach>

            </div>

        </c:otherwise>

    </c:choose>

</div>

<%@ include file="fragments/footer.jspf" %>