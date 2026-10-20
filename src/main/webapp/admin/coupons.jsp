<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Coupon Management - ShopSphere</title>

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            color: #222;
        }

        .navbar {
            background: #1f2a44;
            color: white;
            padding: 18px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar h2 {
            margin: 0;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
        }

        .container {
            width: 92%;
            margin: 30px auto;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            margin-bottom: 25px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.08);
        }

        h1 {
            margin-top: 0;
        }

        h3 {
            margin-top: 0;
        }

        .form-grid {
            display: grid;
            grid-template-columns:
                repeat(auto-fit, minmax(180px, 1fr));
            gap: 15px;
        }

        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .btn {
            border: none;
            padding: 10px 16px;
            border-radius: 6px;
            cursor: pointer;
            color: white;
            background: #2563eb;
        }

        .btn-danger {
            background: #dc2626;
        }

        .btn-success {
            background: #16a34a;
        }

        .table-container {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th,
        td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #1f2a44;
            color: white;
        }

        .active {
            color: green;
            font-weight: bold;
        }

        .inactive {
            color: red;
            font-weight: bold;
        }

        .actions {
            display: flex;
            gap: 8px;
            flex-wrap: wrap;
        }

    </style>

</head>

<body>

<div class="navbar">

    <h2>ShopSphere Admin</h2>

    <div>
        <a href="${pageContext.request.contextPath}/admin/dashboard">
            Dashboard
        </a>

        <a href="${pageContext.request.contextPath}/admin/products">
            Products
        </a>

        <a href="${pageContext.request.contextPath}/admin/orders">
            Orders
        </a>

        <a href="${pageContext.request.contextPath}/admin/coupons">
            Coupons
        </a>
    </div>

</div>


<div class="container">

    <h1>Coupon Management</h1>


    <!-- ADD COUPON -->

    <div class="card">

        <h3>Add New Coupon</h3>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/coupons">

            <input type="hidden"
                   name="action"
                   value="add">

            <div class="form-grid">

                <div>

                    <label>Coupon Code</label>

                    <input type="text"
                           name="code"
                           placeholder="SAVE200"
                           required>

                </div>


                <div>

                    <label>Discount</label>

                    <input type="number"
                           name="discount"
                           step="0.01"
                           min="0"
                           placeholder="200"
                           required>

                </div>


                <div>

                    <label>Expiry Date</label>

                    <input type="date"
                           name="expiryDate">

                </div>


                <div>

                    <label>Active</label>

                    <input type="checkbox"
                           name="active"
                           value="true"
                           checked>

                </div>

            </div>

            <br>

            <button class="btn btn-success"
                    type="submit">

                Add Coupon

            </button>

        </form>

    </div>


    <!-- COUPON LIST -->

    <div class="card">

        <h3>All Coupons</h3>

        <div class="table-container">

            <table>

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Code</th>
                    <th>Discount</th>
                    <th>Expiry Date</th>
                    <th>Status</th>
                    <th>Actions</th>

                </tr>

                </thead>


                <tbody>

                <c:choose>

                    <c:when test="${empty coupons}">

                        <tr>

                            <td colspan="6">
                                No coupons available.
                            </td>

                        </tr>

                    </c:when>


                    <c:otherwise>

                        <c:forEach
                                var="coupon"
                                items="${coupons}">

                            <tr>

                                <td>
                                    ${coupon.couponId}
                                </td>


                                <td>
                                    ${coupon.code}
                                </td>


                                <td>
                                    ₹${coupon.discount}
                                </td>


                                <td>
                                    ${coupon.expiryDate}
                                </td>


                                <td>

                                    <c:choose>

                                        <c:when test="${coupon.active}">

                                            <span class="active">
                                                Active
                                            </span>

                                        </c:when>

                                        <c:otherwise>

                                            <span class="inactive">
                                                Inactive
                                            </span>

                                        </c:otherwise>

                                    </c:choose>

                                </td>


                                <td>

                                    <div class="actions">

                                        <!-- DELETE -->

                                        <form method="post"
                                              action="${pageContext.request.contextPath}/admin/coupons">

                                            <input type="hidden"
                                                   name="action"
                                                   value="delete">

                                            <input type="hidden"
                                                   name="couponId"
                                                   value="${coupon.couponId}">

                                            <button
                                                    class="btn btn-danger"
                                                    type="submit"
                                                    onclick="return confirm('Delete this coupon?');">

                                                Delete

                                            </button>

                                        </form>


                                        <!-- UPDATE -->

                                        <form method="post"
                                              action="${pageContext.request.contextPath}/admin/coupons">

                                            <input type="hidden"
                                                   name="action"
                                                   value="update">

                                            <input type="hidden"
                                                   name="couponId"
                                                   value="${coupon.couponId}">

                                            <input type="hidden"
                                                   name="code"
                                                   value="${coupon.code}">

                                            <input type="hidden"
                                                   name="discount"
                                                   value="${coupon.discount}">

                                            <input type="hidden"
                                                   name="expiryDate"
                                                   value="${coupon.expiryDate}">

                                            <input type="hidden"
                                                   name="active"
                                                   value="${coupon.active}">

                                            <button
                                                    class="btn"
                                                    type="submit">

                                                Update

                                            </button>

                                        </form>

                                    </div>

                                </td>

                            </tr>

                        </c:forEach>

                    </c:otherwise>

                </c:choose>

                </tbody>

            </table>

        </div>

    </div>

</div>

</body>
</html>