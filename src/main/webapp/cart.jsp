<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <h2>My Cart</h2>

    <c:choose>

        <c:when test="${empty cartItems}">

            <div class="alert alert-info mt-4">
                Your cart is empty.
            </div>

            <a href="products" class="btn btn-primary">
                Continue Shopping
            </a>

        </c:when>

        <c:otherwise>

            <table class="table table-bordered mt-4">

                <thead class="table-dark">

                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>

                </thead>

                <tbody>

                <c:forEach var="item" items="${cartItems}">

                    <tr>

                        <td>
                            ${item.productName}
                        </td>

                        <td>
                            ₹ ${item.price}
                        </td>

                        <td>

                            <form action="cart"
                                  method="post"
                                  class="d-flex">

                                <input type="hidden"
                                       name="action"
                                       value="update">

                                <input type="hidden"
                                       name="cartId"
                                       value="${item.cartId}">

                                <input type="number"
                                       name="quantity"
                                       value="${item.quantity}"
                                       min="1"
                                       class="form-control"
                                       style="width:80px;">

                                <button type="submit"
                                        class="btn btn-sm btn-primary ms-2">
                                    Update
                                </button>

                            </form>

                        </td>

                        <td>
                            ₹ ${item.price * item.quantity}
                        </td>

                        <td>

                            <form action="cart"
                                  method="post">

                                <input type="hidden"
                                       name="action"
                                       value="remove">

                                <input type="hidden"
                                       name="cartId"
                                       value="${item.cartId}">

                                <button type="submit"
                                        class="btn btn-sm btn-danger">
                                    Remove
                                </button>

                            </form>

                        </td>

                    </tr>

                </c:forEach>

                </tbody>

            </table>

            <a href="checkout"
               class="btn btn-success">
                Proceed to Checkout
            </a>

            <a href="products"
               class="btn btn-secondary">
                Continue Shopping
            </a>

        </c:otherwise>

    </c:choose>

</div>

<%@ include file="fragments/footer.jspf" %>