<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../fragments/header.jspf" %>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <h2>Admin Dashboard</h2>
            <p class="text-muted mb-0">
                Manage ShopSphere from one place.
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/logout"
           class="btn btn-outline-danger">
            Logout
        </a>
    </div>

    <!-- Summary Cards -->
    <div class="row g-4 mb-5">

        <div class="col-md-3">
            <div class="card shadow-sm h-100">
                <div class="card-body">
                    <h6 class="text-muted">Total Users</h6>
                    <h2>${totalUsers}</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card shadow-sm h-100">
                <div class="card-body">
                    <h6 class="text-muted">Total Products</h6>
                    <h2>${totalProducts}</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card shadow-sm h-100">
                <div class="card-body">
                    <h6 class="text-muted">Total Orders</h6>
                    <h2>${totalOrders}</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card shadow-sm h-100">
                <div class="card-body">
                    <h6 class="text-muted">Total Sales</h6>
                    <h2>₹${totalSales}</h2>
                </div>
            </div>
        </div>

    </div>

    <!-- Management -->
    <div class="card shadow">
        <div class="card-body">

            <h4 class="mb-4">Management</h4>

            <div class="row g-3">

                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/admin/products"
                       class="btn btn-primary w-100">
                        Product Management
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/admin/categories"
                       class="btn btn-primary w-100">
                        Category Management
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/admin/orders"
                       class="btn btn-primary w-100">
                        Order Management
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/admin/users"
                       class="btn btn-secondary w-100">
                        Customer Management
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/admin/inventory"
                       class="btn btn-secondary w-100">
                        Inventory Management
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/admin/low-stock"
                       class="btn btn-warning w-100">
                        Low Stock
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/admin/coupons"
                       class="btn btn-secondary w-100">
                        Coupon Management
                    </a>
                </div>

                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/admin/sales"
                       class="btn btn-success w-100">
                        Sales Summary
                    </a>
                </div>

            </div>

        </div>
    </div>

</div>

<%@ include file="../fragments/footer.jspf" %>