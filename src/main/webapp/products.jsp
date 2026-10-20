<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <h2 class="mb-4">Products</h2>

    <!-- Search + Filter Form -->

    <div class="card shadow-sm mb-4">

        <div class="card-body">

            <h5 class="mb-3">
                Search and Filter Products
            </h5>

            <form action="${pageContext.request.contextPath}/products"
                  method="get">

                <div class="row">

                    <!-- Search -->

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Search
                        </label>

                        <input type="text"
                               name="search"
                               class="form-control"
                               placeholder="Search by name or description"
                               value="${search}">

                    </div>

                    <!-- Category -->

                    <div class="col-md-6 mb-3">
    <label class="form-label">Category</label>

    <select name="categoryId" class="form-select">

        <option value="0">All Categories</option>

        <c:forEach var="category" items="${categories}">
            <option value="${category.categoryId}"
                    ${categoryId == category.categoryId ? 'selected' : ''}>
                ${category.name}
            </option>
        </c:forEach>

    </select>
</div>

                </div>

                <div class="row">

                    <!-- Minimum Price -->

                    <div class="col-md-4 mb-3">

                        <label class="form-label">
                            Minimum Price
                        </label>

                        <input type="number"
                               name="minPrice"
                               class="form-control"
                               placeholder="Minimum price"
                               min="0"
                               step="0.01"
                               value="${minPrice}">

                    </div>

                    <!-- Maximum Price -->

                    <div class="col-md-4 mb-3">

                        <label class="form-label">
                            Maximum Price
                        </label>

                        <input type="number"
                               name="maxPrice"
                               class="form-control"
                               placeholder="Maximum price"
                               min="0"
                               step="0.01"
                               value="${maxPrice}">

                    </div>

                    <!-- Sort -->

                    <div class="col-md-4 mb-3">

                        <label class="form-label">
                            Sort By
                        </label>

                        <select name="sort"
                                class="form-select">

                            <option value="">
                                Default
                            </option>

                            <option value="priceLow"
                                    ${sort == 'priceLow' ? 'selected' : ''}>
                                Price: Low to High
                            </option>

                            <option value="priceHigh"
                                    ${sort == 'priceHigh' ? 'selected' : ''}>
                                Price: High to Low
                            </option>

                            <option value="nameAZ"
                                    ${sort == 'nameAZ' ? 'selected' : ''}>
                                Name: A to Z
                            </option>

                            <option value="nameZA"
                                    ${sort == 'nameZA' ? 'selected' : ''}>
                                Name: Z to A
                            </option>

                        </select>

                    </div>

                </div>

                <!-- Buttons -->

                <button type="submit"
                        class="btn btn-primary">

                    Apply Filters

                </button>

                <a href="${pageContext.request.contextPath}/products"
                   class="btn btn-secondary ms-2">

                    Clear Filters

                </a>

            </form>

        </div>

    </div>


    <!-- Error Message -->

    <c:if test="${not empty error}">

        <div class="alert alert-danger">
            ${error}
        </div>

    </c:if>


    <!-- Product List -->

    <div class="row">

        <c:choose>

            <c:when test="${empty products}">

                <div class="col-12">

                    <div class="alert alert-info">
                        No products found.
                    </div>

                </div>

            </c:when>

            <c:otherwise>

                <c:forEach var="product"
                           items="${products}">

                    <div class="col-md-4 mb-4">

                        <shop:productCard
                                product="${product}" />

                    </div>

                </c:forEach>

            </c:otherwise>

        </c:choose>

    </div>

</div>

<%@ include file="fragments/footer.jspf" %>