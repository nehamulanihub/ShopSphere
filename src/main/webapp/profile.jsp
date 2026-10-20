<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="fragments/header.jspf" %>
<%@ include file="fragments/navbar.jspf" %>

<div class="container mt-5">

    <h2 class="mb-4">My Profile</h2>

    <!-- Profile Messages -->

    <% if (request.getAttribute("success") != null) { %>

        <div class="alert alert-success">
            <%= request.getAttribute("success") %>
        </div>

    <% } %>

    <% if (request.getAttribute("error") != null) { %>

        <div class="alert alert-danger">
            <%= request.getAttribute("error") %>
        </div>

    <% } %>


    <!-- Profile Information -->

    <div class="card shadow mb-5">

        <div class="card-body">

            <h4 class="mb-4">
                Edit Account Information
            </h4>

            <form
                    action="${pageContext.request.contextPath}/profile"
                    method="post">

                <input type="hidden"
                       name="action"
                       value="updateProfile">

                <div class="mb-3">

                    <label class="form-label">
                        User ID
                    </label>

                    <input type="text"
                           class="form-control"
                           value="${user.userId}"
                           readonly>

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Name
                    </label>

                    <input type="text"
                           name="name"
                           class="form-control"
                           value="${user.name}"
                           required>

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Email
                    </label>

                    <input type="email"
                           name="email"
                           class="form-control"
                           value="${user.email}"
                           required>

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Phone
                    </label>

                    <input type="text"
                           name="phone"
                           class="form-control"
                           value="${user.phone}">

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Role
                    </label>

                    <input type="text"
                           class="form-control"
                           value="${user.role}"
                           readonly>

                </div>


                <button type="submit"
                        class="btn btn-success">

                    Update Profile

                </button>

                <a href="${pageContext.request.contextPath}/products"
                   class="btn btn-primary ms-2">

                    Continue Shopping

                </a>

            </form>

        </div>

    </div>


    <!-- Change Password -->

    <div class="card shadow mb-5">

        <div class="card-body">

            <h4 class="mb-4">
                Change Password
            </h4>


            <% if (request.getAttribute("passwordSuccess") != null) { %>

                <div class="alert alert-success">

                    <%= request.getAttribute("passwordSuccess") %>

                </div>

            <% } %>


            <% if (request.getAttribute("passwordError") != null) { %>

                <div class="alert alert-danger">

                    <%= request.getAttribute("passwordError") %>

                </div>

            <% } %>


            <form
                    action="${pageContext.request.contextPath}/profile"
                    method="post">

                <input type="hidden"
                       name="action"
                       value="changePassword">


                <div class="mb-3">

                    <label class="form-label">
                        Current Password
                    </label>

                    <input type="password"
                           name="currentPassword"
                           class="form-control"
                           required>

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        New Password
                    </label>

                    <input type="password"
                           name="newPassword"
                           class="form-control"
                           minlength="6"
                           required>

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Confirm New Password
                    </label>

                    <input type="password"
                           name="confirmPassword"
                           class="form-control"
                           minlength="6"
                           required>

                </div>


                <button type="submit"
                        class="btn btn-warning">

                    Change Password

                </button>

            </form>

        </div>

    </div>

</div>


<%@ include file="fragments/footer.jspf" %>