<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../fragments/header.jspf" %>

<div class="container mt-5">

    <!-- Header -->

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h2>Manage Products</h2>

        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn btn-secondary">
            Dashboard
        </a>

    </div>


    <!-- Add Product -->

    <div class="card shadow mb-5">

        <div class="card-body">

            <h4 class="mb-4">Add New Product</h4>

            <form action="${pageContext.request.contextPath}/admin/products"
                  method="post">

                <input type="hidden"
                       name="action"
                       value="add">


                <div class="row">

                    <!-- Product Name -->

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Product Name
                        </label>

                        <input type="text"
                               name="name"
                               class="form-control"
                               maxlength="150"
                               required>

                    </div>


                    <!-- Category -->

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Category ID
                        </label>

                        <input type="number"
                               name="categoryId"
                               class="form-control"
                               min="1"
                               required>

                    </div>

                </div>


                <!-- Description -->

                <div class="mb-3">

                    <label class="form-label">
                        Description
                    </label>

                    <textarea name="description"
                              class="form-control"
                              rows="3"
                              maxlength="1000"
                              required></textarea>

                </div>


                <div class="row">

                    <!-- Price -->

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Price
                        </label>

                        <input type="number"
                               name="price"
                               class="form-control"
                               min="0.01"
                               step="0.01"
                               required>

                    </div>


                    <!-- Stock -->

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Stock
                        </label>

                        <input type="number"
                               name="stock"
                               class="form-control"
                               min="0"
                               required>

                    </div>

                </div>


                <!-- Image URL -->

                <div class="mb-3">

                    <label class="form-label">
                        Image URL
                    </label>

                    <input type="url"
                           name="imageUrl"
                           class="form-control"
                           placeholder="https://example.com/image.jpg">

                </div>


                <button type="submit"
                        class="btn btn-primary">
                    Add Product
                </button>

            </form>

        </div>

    </div>



    <!-- Product List -->

    <div class="card shadow">

        <div class="card-body">

            <h4 class="mb-4">
                Product List
            </h4>


            <c:choose>

                <c:when test="${empty products}">

                    <div class="alert alert-info">
                        No products available.
                    </div>

                </c:when>


                <c:otherwise>

                    <div class="table-responsive">

                        <table class="table table-bordered table-hover align-middle">

                            <thead class="table-dark">

                                <tr>

                                    <th>ID</th>

                                    <th>Name</th>

                                    <th>Price</th>

                                    <th>Stock</th>

                                    <th>Category</th>

                                    <th style="min-width: 260px;">
                                        Action
                                    </th>

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
                                            ${product.categoryId}
                                        </td>


                                        <td>

                                            <!-- Edit Button -->

                                            <button type="button"
                                                    class="btn btn-warning btn-sm"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#editProduct${product.productId}">
                                                Edit
                                            </button>


                                            <!-- Delete -->

                                            <form action="${pageContext.request.contextPath}/admin/products"
                                                  method="post"
                                                  class="d-inline"
                                                  onsubmit="return confirm('Are you sure you want to delete this product?');">

                                                <input type="hidden"
                                                       name="action"
                                                       value="delete">

                                                <input type="hidden"
                                                       name="productId"
                                                       value="${product.productId}">

                                                <button type="submit"
                                                        class="btn btn-danger btn-sm">
                                                    Delete
                                                </button>

                                            </form>

                                        </td>

                                    </tr>



                                    <!-- Edit Product Modal -->

                                    <div class="modal fade"
                                         id="editProduct${product.productId}"
                                         tabindex="-1">

                                        <div class="modal-dialog modal-lg">

                                            <div class="modal-content">

                                                <div class="modal-header">

                                                    <h5 class="modal-title">
                                                        Edit Product
                                                    </h5>

                                                    <button type="button"
                                                            class="btn-close"
                                                            data-bs-dismiss="modal">
                                                    </button>

                                                </div>


                                                <form action="${pageContext.request.contextPath}/admin/products"
                                                      method="post">

                                                    <div class="modal-body">

                                                        <input type="hidden"
                                                               name="action"
                                                               value="update">

                                                        <input type="hidden"
                                                               name="productId"
                                                               value="${product.productId}">


                                                        <!-- Name -->

                                                        <div class="mb-3">

                                                            <label class="form-label">
                                                                Product Name
                                                            </label>

                                                            <input type="text"
                                                                   name="name"
                                                                   class="form-control"
                                                                   value="${product.name}"
                                                                   maxlength="150"
                                                                   required>

                                                        </div>


                                                        <!-- Category -->

                                                        <div class="mb-3">

                                                            <label class="form-label">
                                                                Category ID
                                                            </label>

                                                            <input type="number"
                                                                   name="categoryId"
                                                                   class="form-control"
                                                                   value="${product.categoryId}"
                                                                   min="1"
                                                                   required>

                                                        </div>


                                                        <!-- Description -->

                                                        <div class="mb-3">

                                                            <label class="form-label">
                                                                Description
                                                            </label>

                                                            <textarea name="description"
                                                                      class="form-control"
                                                                      rows="3"
                                                                      maxlength="1000"
                                                                      required>${product.description}</textarea>

                                                        </div>


                                                        <div class="row">

                                                            <!-- Price -->

                                                            <div class="col-md-6 mb-3">

                                                                <label class="form-label">
                                                                    Price
                                                                </label>

                                                                <input type="number"
                                                                       name="price"
                                                                       class="form-control"
                                                                       value="${product.price}"
                                                                       min="0.01"
                                                                       step="0.01"
                                                                       required>

                                                            </div>


                                                            <!-- Stock -->

                                                            <div class="col-md-6 mb-3">

                                                                <label class="form-label">
                                                                    Stock
                                                                </label>

                                                                <input type="number"
                                                                       name="stock"
                                                                       class="form-control"
                                                                       value="${product.stock}"
                                                                       min="0"
                                                                       required>

                                                            </div>

                                                        </div>


                                                        <!-- Image URL -->

                                                        <div class="mb-3">

                                                            <label class="form-label">
                                                                Image URL
                                                            </label>

                                                            <input type="url"
                                                                   name="imageUrl"
                                                                   class="form-control"
                                                                   value="${product.imageUrl}">

                                                        </div>

                                                    </div>


                                                    <div class="modal-footer">

                                                        <button type="button"
                                                                class="btn btn-secondary"
                                                                data-bs-dismiss="modal">
                                                            Cancel
                                                        </button>

                                                        <button type="submit"
                                                                class="btn btn-primary">
                                                            Update Product
                                                        </button>

                                                    </div>

                                                </form>

                                            </div>

                                        </div>

                                    </div>

                                </c:forEach>

                            </tbody>

                        </table>

                    </div>

                </c:otherwise>

            </c:choose>

        </div>

    </div>

</div>


<!-- Bootstrap JS -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>


<%@ include file="../fragments/footer.jspf" %>