<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <h2 class="mb-4">
        My Addresses
    </h2>

    <!-- Add Address -->

    <div class="card shadow mb-5">

        <div class="card-body">

            <h4 class="mb-4">
                Add New Address
            </h4>

            <form action="${pageContext.request.contextPath}/addresses"
                  method="post">

                <input type="hidden"
                       name="action"
                       value="add">

                <div class="mb-3">

                    <label class="form-label">
                        Address
                    </label>

                    <textarea name="addressLine"
                              class="form-control"
                              rows="3"
                              required></textarea>

                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            City
                        </label>

                        <input type="text"
                               name="city"
                               class="form-control"
                               required>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            State
                        </label>

                        <input type="text"
                               name="state"
                               class="form-control"
                               required>

                    </div>

                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Pincode
                        </label>

                        <input type="text"
                               name="pincode"
                               class="form-control"
                               required>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Country
                        </label>

                        <input type="text"
                               name="country"
                               class="form-control"
                               value="India"
                               required>

                    </div>

                </div>

                <button type="submit"
                        class="btn btn-primary">

                    Add Address

                </button>

            </form>

        </div>

    </div>


    <!-- Saved Addresses -->

    <h4 class="mb-3">
        Saved Addresses
    </h4>

    <div class="row">

        <c:choose>

            <c:when test="${empty addresses}">

                <div class="col-12">

                    <div class="alert alert-info">
                        No addresses saved yet.
                    </div>

                </div>

            </c:when>

            <c:otherwise>

                <c:forEach var="address"
                           items="${addresses}">

                    <div class="col-md-6 mb-4">

                        <div class="card shadow-sm h-100">

                            <div class="card-body">

                                <h5 class="card-title">
                                    Address
                                </h5>

                                <p class="card-text">

                                    ${address.addressLine}<br>

                                    ${address.city},
                                    ${address.state}
                                    - ${address.pincode}<br>

                                    ${address.country}

                                </p>

                                <form action="${pageContext.request.contextPath}/addresses"
                                      method="post">

                                    <input type="hidden"
                                           name="action"
                                           value="delete">

                                    <input type="hidden"
                                           name="addressId"
                                           value="${address.addressId}">

                                    <button type="submit"
                                            class="btn btn-danger btn-sm">

                                        Delete

                                    </button>

                                </form>

                            </div>

                        </div>

                    </div>

                </c:forEach>

            </c:otherwise>

        </c:choose>

    </div>

</div>

<%@ include file="fragments/footer.jspf" %>