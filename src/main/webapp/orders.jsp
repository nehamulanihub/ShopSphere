<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.shopsphere.model.Order" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <h2 class="mb-4">
        My Orders
    </h2>

    <%
        List<Order> orders =
                (List<Order>)
                request.getAttribute("orders");
    %>

    <% if (orders == null ||
           orders.isEmpty()) { %>

        <div class="alert alert-info">

            You have not placed any orders yet.

            <div class="mt-3">

                <a href="<%= request.getContextPath() %>/products"
                   class="btn btn-primary">
                    Start Shopping
                </a>

            </div>

        </div>

    <% } else { %>

        <div class="table-responsive">

            <table class="table table-bordered table-hover">

                <thead class="table-dark">

                    <tr>

                        <th>Order ID</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Date</th>
                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    <% for (Order order : orders) { %>

                        <tr>

                            <td>
                                #<%= order.getOrderId() %>
                            </td>

                            <td>
                                ₹<%= order.getTotalAmount() %>
                            </td>

                            <td>
                                <span class="badge bg-primary">
                                    <%= order.getStatus() %>
                                </span>
                            </td>

                            <td>
                                <%= order.getOrderDate() %>
                            </td>

                            <td>

                                <a href="<%= request.getContextPath() %>/orders?id=<%= order.getOrderId() %>"
                                   class="btn btn-sm btn-outline-primary">
                                    View Details
                                </a>

                            </td>

                        </tr>

                    <% } %>

                </tbody>

            </table>

        </div>

    <% } %>

</div>

<%@ include file="fragments/footer.jspf" %>