<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../fragments/header.jspf" %>

<div class="container mt-5">

    <!-- Header -->

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h2>Manage Categories</h2>

        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn btn-secondary">
            Dashboard
        </a>

    </div>


    <!-- Add Category -->

    <div class="card shadow mb-5">

        <div class="card-body">

            <h4 class="mb-4">Add New Category</h4>

            <form action="${pageContext.request.contextPath}/admin/categories"
                  method="post">

                <input type="hidden"
                       name="action"
                       value="add">


                <div class="mb-3">

                    <label class="form-label">
                        Category Name
                    </label>

                    <input type="text"
                           name="name"
                           class="form-control"
                           maxlength="100"
                           required>

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Description
                    </label>

                    <textarea name="description"
                              class="form-control"
                              rows="3"
                              maxlength="500"></textarea>

                </div>


                <button type="submit"
                        class="btn btn-primary">
                    Add Category
                </button>

            </form>

        </div>

    </div>


    <!-- Category List -->

    <div class="card shadow">

        <div class="card-body">

            <h4 class="mb-4">
                Category List
            </h4>


            <c:choose>

                <c:when test="${empty categories}">

                    <div class="alert alert-info">
                        No categories available.
                    </div>

                </c:when>


                <c:otherwise>

                    <div class="table-responsive">

                        <table class="table table-bordered table-hover align-middle">

                            <thead class="table-dark">

                                <tr>

                                    <th>ID</th>

                                    <th>Name</th>

                                    <th>Description</th>

                                    <th style="min-width: 180px;">
                                        Action
                                    </th>

                                </tr>

                            </thead>


                            <tbody>

                                <c:forEach var="category"
                                           items="${categories}">

                                    <tr>

                                        <td>
                                            ${category.categoryId}
                                        </td>


                                        <td>
                                            ${category.name}
                                        </td>


                                        <td>

                                            <c:choose>

                                                <c:when test="${empty category.description}">
                                                    <span class="text-muted">
                                                        No description
                                                    </span>
                                                </c:when>

                                                <c:otherwise>
                                                    ${category.description}
                                                </c:otherwise>

                                            </c:choose>

                                        </td>


                                        <td>

                                            <!-- Edit -->

                                            <button type="button"
                                                    class="btn btn-warning btn-sm"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#editCategory${category.categoryId}">
                                                Edit
                                            </button>


                                            <!-- Delete -->

                                            <form action="${pageContext.request.contextPath}/admin/categories"
                                                  method="post"
                                                  class="d-inline"
                                                  onsubmit="return confirm('Are you sure you want to delete this category?');">

                                                <input type="hidden"
                                                       name="action"
                                                       value="delete">

                                                <input type="hidden"
                                                       name="categoryId"
                                                       value="${category.categoryId}">

                                                <button type="submit"
                                                        class="btn btn-danger btn-sm">
                                                    Delete
                                                </button>

                                            </form>

                                        </td>

                                    </tr>


                                    <!-- Edit Category Modal -->

                                    <div class="modal fade"
                                         id="editCategory${category.categoryId}"
                                         tabindex="-1">

                                        <div class="modal-dialog">

                                            <div class="modal-content">

                                                <div class="modal-header">

                                                    <h5 class="modal-title">
                                                        Edit Category
                                                    </h5>

                                                    <button type="button"
                                                            class="btn-close"
                                                            data-bs-dismiss="modal">
                                                    </button>

                                                </div>


                                                <form action="${pageContext.request.contextPath}/admin/categories"
                                                      method="post">

                                                    <div class="modal-body">

                                                        <input type="hidden"
                                                               name="action"
                                                               value="update">

                                                        <input type="hidden"
                                                               name="categoryId"
                                                               value="${category.categoryId}">


                                                        <div class="mb-3">

                                                            <label class="form-label">
                                                                Category Name
                                                            </label>

                                                            <input type="text"
                                                                   name="name"
                                                                   class="form-control"
                                                                   value="${category.name}"
                                                                   maxlength="100"
                                                                   required>

                                                        </div>


                                                        <div class="mb-3">

                                                            <label class="form-label">
                                                                Description
                                                            </label>

                                                            <textarea name="description"
                                                                      class="form-control"
                                                                      rows="3"
                                                                      maxlength="500">${category.description}</textarea>

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
                                                            Update Category
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