<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="../fragments/header.jspf" %>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>
            <h2>Sales Summary</h2>
            <p class="text-muted mb-0">
                Overview of ShopSphere sales.
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn btn-secondary">
            Dashboard
        </a>

    </div>

    <div class="row g-4">

        <div class="col-md-4">

            <div class="card shadow-sm h-100">

                <div class="card-body">

                    <h6 class="text-muted">
                        Completed Orders
                    </h6>

                    <h2>
                        ${totalOrders}
                    </h2>

                </div>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card shadow-sm h-100">

                <div class="card-body">

                    <h6 class="text-muted">
                        Total Sales
                    </h6>

                    <h2>
                        ₹${totalSales}
                    </h2>

                </div>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card shadow-sm h-100">

                <div class="card-body">

                    <h6 class="text-muted">
                        Average Order Value
                    </h6>

                    <h2>
                        ₹${averageOrderValue}
                    </h2>

                </div>

            </div>

        </div>

    </div>

</div>

<%@ include file="../fragments/footer.jspf" %>