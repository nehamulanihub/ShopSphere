<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.shopsphere.model.Address" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-7">

            <div class="card shadow">

                <div class="card-body">

                    <h2 class="mb-4">
                        Checkout
                    </h2>


                    <!-- Error Message -->

                    <% if (request.getAttribute("error") != null) { %>

                        <div class="alert alert-danger">
                            <%= request.getAttribute("error") %>
                        </div>

                    <% } %>


                    <form action="checkout" method="post">


                        <!-- DELIVERY ADDRESS -->

                        <div class="mb-4">

                            <label class="form-label fw-bold">
                                Delivery Address
                            </label>

                            <%
                                List<Address> addresses =
                                        (List<Address>)
                                        request.getAttribute("addresses");
                            %>

                            <% if (addresses != null &&
                                   !addresses.isEmpty()) { %>

                                <select name="addressId"
                                        class="form-select"
                                        required>

                                    <option value="">
                                        Select Delivery Address
                                    </option>

                                    <% for (Address address : addresses) { %>

                                        <option value="<%= address.getAddressId() %>">

                                            <%= address.getAddressLine() %>,
                                            <%= address.getCity() %>,
                                            <%= address.getState() %>,
                                            <%= address.getPincode() %>,
                                            <%= address.getCountry() %>

                                        </option>

                                    <% } %>

                                </select>

                            <% } else { %>

                                <div class="alert alert-warning">

                                    No delivery address found.

                                    <br>

                                    Please add an address before placing your order.

                                </div>

                                <a href="address"
                                   class="btn btn-outline-primary">
                                    Add Address
                                </a>

                            <% } %>

                        </div>


                        <!-- COUPON -->

                        <div class="mb-3">

                            <label class="form-label">
                                Coupon Code
                            </label>

                            <input type="text"
                                   name="couponCode"
                                   class="form-control"
                                   placeholder="Enter coupon code (optional)">

                        </div>


                        <!-- PAYMENT METHOD -->

                        <div class="mb-3">

                            <label class="form-label">
                                Payment Method
                            </label>

                            <select name="paymentMethod"
                                    class="form-select"
                                    required>

                                <option value="">
                                    Select Payment Method
                                </option>

                                <option value="COD">
                                    Cash on Delivery
                                </option>

                                <option value="CARD">
                                    Credit / Debit Card
                                </option>

                                <option value="UPI">
                                    UPI
                                </option>

                            </select>

                        </div>


                        <!-- PLACE ORDER -->

                        <button type="submit"
                                class="btn btn-success w-100"
                                <% if (addresses == null ||
                                       addresses.isEmpty()) { %>
                                    disabled
                                <% } %>>

                            Place Order

                        </button>

                    </form>

                </div>

            </div>

        </div>

    </div>

</div>

<%@ include file="fragments/footer.jspf" %>