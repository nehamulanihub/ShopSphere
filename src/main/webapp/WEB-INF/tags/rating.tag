<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="rating" required="true" type="java.lang.Integer" %>

<div class="mb-2">

    <span class="text-warning">
        <c:forEach begin="1" end="${rating}">
            ★
        </c:forEach>
    </span>

    <span class="text-muted">
        (${rating}/5)
    </span>

</div>