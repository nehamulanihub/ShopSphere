<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../fragments/header.jspf" %>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>
            <h2>Customer Management</h2>
            <p class="text-muted mb-0">
                View registered ShopSphere users.
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn btn-secondary">
            Dashboard
        </a>

    </div>

    <div class="card shadow">

        <div class="card-body">

            <h4 class="mb-4">
                Registered Users
            </h4>

            <c:choose>

                <c:when test="${empty users}">

                    <div class="alert alert-info">
                        No users registered yet.
                    </div>

                </c:when>

                <c:otherwise>

                    <div class="table-responsive">

                        <table class="table table-bordered table-hover align-middle">

                            <thead class="table-dark">

                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Role</th>
                                </tr>

                            </thead>

                            <tbody>

                                <c:forEach var="user" items="${users}">

                                    <tr>

                                        <td>
                                            ${user.userId}
                                        </td>

                                        <td>
                                            ${user.name}
                                        </td>

                                        <td>
                                            ${user.email}
                                        </td>

                                        <td>
                                            <c:choose>

                                                <c:when test="${empty user.phone}">
                                                    <span class="text-muted">
                                                        Not provided
                                                    </span>
                                                </c:when>

                                                <c:otherwise>
                                                    ${user.phone}
                                                </c:otherwise>

                                            </c:choose>
                                        </td>

                                        <td>

                                            <c:choose>

                                                <c:when test="${user.role == 'ADMIN'}">
                                                    <span class="badge bg-danger">
                                                        ADMIN
                                                    </span>
                                                </c:when>

                                                <c:otherwise>
                                                    <span class="badge bg-primary">
                                                        ${user.role}
                                                    </span>
                                                </c:otherwise>

                                            </c:choose>

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