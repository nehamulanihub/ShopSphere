<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.shopsphere.model.Order" %>
<%@ page import="com.shopsphere.model.OrderItem" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <%
        Order order =
                (Order) request.getAttribute("order");

        List<OrderItem> orderItems =
                (List<OrderItem>)
                request.getAttribute("orderItems");
    %>

    <% if (order != null) { %>

        <div class="card shadow">

            <div class="card-body">

                <h2 class="mb-4">
                    Order #<%= order.getOrderId() %>
                </h2>

                <p>
                    <strong>Status:</strong>
                    <span class="badge bg-primary">
                        <%= order.getStatus() %>
                    </span>
                </p>

                <p>
                    <strong>Total Amount:</strong>
                    ₹<%= order.getTotalAmount() %>
                </p>

                <p>
                    <strong>Order Date:</strong>
                    <%= order.getOrderDate() %>
                </p>

                <hr>

                <h4 class="mb-3">
                    Order Items
                </h4>

                <% if (orderItems != null &&
                       !orderItems.isEmpty()) { %>

                    <div class="table-responsive">

                        <table class="table table-bordered">

                            <thead>

                                <tr>
                                    <th>Product ID</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>

                            </thead>

                            <tbody>

                                <% for (OrderItem item :
                                      orderItems) { %>

                                    <tr>

                                        <td>
                                            <%= item.getProductId() %>
                                        </td>

                                        <td>
                                            <%= item.getQuantity() %>
                                        </td>

                                        <td>
                                            ₹<%= item.getPrice() %>
                                        </td>

                                    </tr>

                                <% } %>

                            </tbody>

                        </table>

                    </div>

                <% } else { %>

                    <div class="alert alert-info">
                        No order items found.
                    </div>

                <% } %>

                <a href="<%= request.getContextPath() %>/orders"
                   class="btn btn-secondary">
                    Back to Orders
                </a>

            </div>

        </div>

    <% } %>

</div>

<%@ include file="fragments/footer.jspf" %>