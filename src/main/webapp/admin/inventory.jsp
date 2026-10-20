<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../fragments/header.jspf" %>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>
            <h2>Inventory Management</h2>
            <p class="text-muted mb-0">
                Manage product stock levels.
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn btn-secondary">
            Dashboard
        </a>

    </div>

    <div class="card shadow">

        <div class="card-body">

            <div class="table-responsive">

                <table class="table table-bordered table-hover align-middle">

                    <thead class="table-dark">

                        <tr>
                            <th>ID</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Current Stock</th>
                            <th>Update Stock</th>
                        </tr>

                    </thead>

                    <tbody>

                        <c:forEach var="product"
                                   items="${products}">

                            <tr>

                                <td>
                                    ${product.productId}
                                </td>

                                <td>
                                    ${product.name}
                                </td>

                                <td>
                                    ₹${product.price}
                                </td>

                                <td>

                                    <c:choose>

                                        <c:when test="${product.stock == 0}">
                                            <span class="badge bg-danger">
                                                Out of Stock
                                            </span>
                                        </c:when>

                                        <c:when test="${product.stock <= 5}">
                                            <span class="badge bg-warning text-dark">
                                                ${product.stock} Low
                                            </span>
                                        </c:when>

                                        <c:otherwise>
                                            <span class="badge bg-success">
                                                ${product.stock}
                                            </span>
                                        </c:otherwise>

                                    </c:choose>

                                </td>

                                <td>

                                    <form action="${pageContext.request.contextPath}/admin/inventory"
                                          method="post"
                                          class="d-flex gap-2">

                                        <input type="hidden"
                                               name="productId"
                                               value="${product.productId}">

                                        <input type="number"
                                               name="stock"
                                               value="${product.stock}"
                                               min="0"
                                               class="form-control"
                                               style="max-width: 120px;"
                                               required>

                                        <button type="submit"
                                                class="btn btn-primary btn-sm">
                                            Update
                                        </button>

                                    </form>

                                </td>

                            </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>

        </div>

    </div>

</div>

<%@ include file="../fragments/footer.jspf" %>