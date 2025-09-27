<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/24/2025
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .product-list {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        margin-top: 20px;
    }

    .product-class {
        border: 1px solid #ccc;
        padding: 12px;
        border-radius: 6px;
        width: 220px;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        align-items: center ;
    }
    .hinh {
        width: 120px;
        height: 120px;
        object-fit: contain;
        margin-bottom: 8px;
    }
</style>
<html>
<head>
    <title>Product list</title>
</head>
<body>
<p>
    <a href="cart">View cart</a>

</p>

<div class="product-list">
    <c:forEach items="${products}" var="p">
        <div class="product-class">
            <b>${p.model}</b><br>

        <img src="images/${p.imgurl}" class="hinh"> <br>
        Price: ${p.price} <br>
        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="text" size="2" value="1" name="quantity"> <br>
            <input type="hidden" name="id" value="${p.id}">
            <input type="hidden" name="price" value="${p.price}">
            <input type="hidden" name="model" value="${p.model}">
            <input type="hidden" name="action" value="add"> <br>
            <input type="submit" name="addToCart" value="Add To Cart"> <br>
        </form>
        <a href="${pageContext.request.contextPath}/product?id=${p.id}">Product Detail</a><br>
        </div>

    </c:forEach>
</div>
</body>
</html>
