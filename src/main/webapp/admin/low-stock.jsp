<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../fragments/header.jspf" %>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>
            <h2>Low Stock Products</h2>
            <p class="text-muted mb-0">
                Products having 5 or fewer items in stock.
            </p>
        </div>

        <div>

            <a href="${pageContext.request.contextPath}/admin/inventory"
               class="btn btn-primary">
                Inventory
            </a>

            <a href="${pageContext.request.contextPath}/admin/dashboard"
               class="btn btn-secondary">
                Dashboard
            </a>

        </div>

    </div>

    <div class="card shadow">

        <div class="card-body">

            <c:choose>

                <c:when test="${empty products}">

                    <div class="alert alert-success">
                        No low-stock products.
                    </div>

                </c:when>

                <c:otherwise>

                    <div class="table-responsive">

                        <table class="table table-bordered table-hover align-middle">

                            <thead class="table-dark">

                                <tr>
                                    <th>ID</th>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Stock</th>
                                    <th>Action</th>
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
                                                        OUT OF STOCK
                                                    </span>
                                                </c:when>

                                                <otherwise>
                                                    <span class="badge bg-warning text-dark">
                                                        ${product.stock}
                                                    </span>
                                                </c:otherwise>

                                            </c:choose>

                                        </td>

                                        <td>

                                            <a href="${pageContext.request.contextPath}/admin/inventory"
                                               class="btn btn-sm btn-primary">
                                                Update Stock
                                            </a>

                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>

                    </div>

                </c:otherwise>

            </c:choose>

        </div>

    </div>

</div>

<%@ include file="../fragments/footer.jspf" %>