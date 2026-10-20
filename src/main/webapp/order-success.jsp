<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-7">

            <div class="card shadow text-center">

                <div class="card-body p-5">

                    <h1 class="text-success mb-3">
                        Order Placed Successfully!
                    </h1>

                    <p class="lead">
                        Thank you for shopping with ShopSphere.
                    </p>

                    <%
                        String orderId =
                                request.getParameter("orderId");
                    %>

                    <% if (orderId != null &&
                           !orderId.trim().isEmpty()) { %>

                        <p>
                            Your Order ID is:
                            <strong>#<%= orderId %></strong>
                        </p>

                    <% } %>

                    <div class="mt-4">

                        <a href="<%= request.getContextPath() %>/orders"
                           class="btn btn-primary me-2">
                            View My Orders
                        </a>

                        <a href="<%= request.getContextPath() %>/products"
                           class="btn btn-outline-secondary">
                            Continue Shopping
                        </a>

                    </div>

                </div>

            </div>

        </div>

    </div>

</div>

<%@ include file="fragments/footer.jspf" %>