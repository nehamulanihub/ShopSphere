
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="product" required="true" type="com.shopsphere.model.Product" %>

<div class="card h-100 shadow-sm">

   

<div class="text-center py-4"
     style="background:#f8f9fa; font-size:64px; min-height:150px;">

    
<c:choose>
    <c:when test="${product.name.toLowerCase().contains('mouse')}">&#128433;</c:when>
    <c:when test="${product.name.toLowerCase().contains('keyboard')}">&#9000;</c:when>
    <c:when test="${product.name.toLowerCase().contains('headphone')}">&#127911;</c:when>
    <c:when test="${product.name.toLowerCase().contains('speaker')}">&#128266;</c:when>
    <c:when test="${product.name.toLowerCase().contains('laptop')}">&#128187;</c:when>
    <c:when test="${product.name.toLowerCase().contains('cover')}">&#128241;</c:when>
    <c:when test="${product.name.toLowerCase().contains('phone')}">&#128241;</c:when>
    <c:when test="${product.name.toLowerCase().contains('watch')}">&#8986;</c:when>
    <c:when test="${product.name.toLowerCase().contains('camera')}">&#128247;</c:when>
    <c:when test="${product.name.toLowerCase().contains('charger')}">&#128268;</c:when>
    <c:when test="${product.name.toLowerCase().contains('book')}">&#128218;</c:when>
    <c:when test="${product.name.toLowerCase().contains('bottle')}">&#127866;</c:when>
    <c:when test="${product.name.toLowerCase().contains('lamp')}">&#128161;</c:when>
    <c:when test="${product.name.toLowerCase().contains('backpack')}">&#127890;</c:when>
    <c:when test="${product.name.toLowerCase().contains('tshirt') or product.name.toLowerCase().contains('t-shirt') or product.name.toLowerCase().contains('t shirt')}">&#128085;</c:when>
    <c:otherwise>&#128722;</c:otherwise>
</c:choose>


</div>



    <div class="card-body d-flex flex-column">

        <h5 class="card-title">
            ${product.name}
        </h5>

        <p class="card-text">
            ${product.description}
        </p>

        <h6 class="text-primary">
            &#8377;${product.price}
        </h6>

        <p class="text-muted">
            Stock: ${product.stock}
        </p>

        <a href="${pageContext.request.contextPath}/products?id=${product.productId}"
           class="btn btn-primary mt-auto">
            View Details
        </a>

    </div>

</div>
