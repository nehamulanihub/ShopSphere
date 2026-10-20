<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../fragments/header.jspf" %>

<div class="container mt-5">

    <!-- Header -->

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h2>Manage Orders</h2>

        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn btn-secondary">
            Dashboard
        </a>

    </div>


    <!-- Orders -->

    <div class="card shadow">

        <div class="card-body">

            <h4 class="mb-4">
                All Orders
            </h4>


            <c:choose>

                <c:when test="${empty orders}">

                    <div class="alert alert-info">
                        No orders available.
                    </div>

                </c:when>


                <c:otherwise>

                    <div class="table-responsive">

                        <table class="table table-bordered table-hover align-middle">

                            <thead class="table-dark">

                                <tr>

                                    <th>Order ID</th>

                                    <th>User ID</th>

                                    <th>Total Amount</th>

                                    <th>Status</th>

                                    <th>Order Date</th>

                                    <th>Update Status</th>

                                </tr>

                            </thead>


                            <tbody>

                                <c:forEach var="order"
                                           items="${orders}">

                                    <tr>

                                        <!-- Order ID -->

                                        <td>
                                            #${order.orderId}
                                        </td>


                                        <!-- User ID -->

                                        <td>
                                            ${order.userId}
                                        </td>


                                        <!-- Amount -->

                                        <td>
                                            ₹${order.totalAmount}
                                        </td>


                                        <!-- Current Status -->

                                        <td>

                                            <c:choose>

                                                <c:when test="${order.status == 'PLACED'}">

                                                    <span class="badge bg-primary">
                                                        PLACED
                                                    </span>

                                                </c:when>

                                                <c:when test="${order.status == 'PROCESSING'}">

                                                    <span class="badge bg-warning text-dark">
                                                        PROCESSING
                                                    </span>

                                                </c:when>

                                                <c:when test="${order.status == 'SHIPPED'}">

                                                    <span class="badge bg-info text-dark">
                                                        SHIPPED
                                                    </span>

                                                </c:when>

                                                <c:when test="${order.status == 'DELIVERED'}">

                                                    <span class="badge bg-success">
                                                        DELIVERED
                                                    </span>

                                                </c:when>

                                                <c:when test="${order.status == 'CANCELLED'}">

                                                    <span class="badge bg-danger">
                                                        CANCELLED
                                                    </span>

                                                </c:when>

                                                <c:otherwise>

                                                    <span class="badge bg-secondary">
                                                        ${order.status}
                                                    </span>

                                                </c:otherwise>

                                            </c:choose>

                                        </td>


                                        <!-- Date -->

                                        <td>
                                            ${order.orderDate}
                                        </td>


                                        <!-- Update Status -->

                                        <td>

                                            <form action="${pageContext.request.contextPath}/admin/orders"
                                                  method="post"
                                                  class="d-flex gap-2">

                                                <input type="hidden"
                                                       name="action"
                                                       value="updateStatus">

                                                <input type="hidden"
                                                       name="orderId"
                                                       value="${order.orderId}">


                                                <select name="status"
                                                        class="form-select form-select-sm">

                                                    <option value="PLACED"
                                                        <c:if test="${order.status == 'PLACED'}">
                                                            selected
                                                        </c:if>>
                                                        PLACED
                                                    </option>


                                                    <option value="PROCESSING"
                                                        <c:if test="${order.status == 'PROCESSING'}">
                                                            selected
                                                        </c:if>>
                                                        PROCESSING
                                                    </option>


                                                    <option value="SHIPPED"
                                                        <c:if test="${order.status == 'SHIPPED'}">
                                                            selected
                                                        </c:if>>
                                                        SHIPPED
                                                    </option>


                                                    <option value="DELIVERED"
                                                        <c:if test="${order.status == 'DELIVERED'}">
                                                            selected
                                                        </c:if>>
                                                        DELIVERED
                                                    </option>


                                                    <option value="CANCELLED"
                                                        <c:if test="${order.status == 'CANCELLED'}">
                                                            selected
                                                        </c:if>>
                                                        CANCELLED
                                                    </option>

                                                </select>


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

                </c:otherwise>

            </c:choose>

        </div>

    </div>

</div>

<%@ include file="../fragments/footer.jspf" %>