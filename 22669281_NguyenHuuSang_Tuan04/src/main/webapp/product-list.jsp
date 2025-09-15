<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <style>
        .product-class { border: 1px solid #ccc; padding: 12px; margin: 12px 0; border-radius: 6px; }
        .hinh { width: 120px; height: 120px; object-fit: contain; display:block; margin-bottom:8px;}
    </style>
</head>
<body>
<a href="cart">View Cart</a>
<c:forEach items="${products}" var="p">
    <div class="product-class">
        <b>${p.model}</b><br>
        <img src="images/${p.image}" class="hinh" /><br>
        Price: ${p.price}<br>
        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="text" size="2" value="1" name="quantity"/> <br/>
            <input type="hidden" name="id" value="${p.id}"/>
            <input type="hidden" name="action" value="add"/>
            <input type="submit" value="Add To Cart"/><br/>
        </form>
        <a href="${pageContext.request.contextPath}/product?id=${p.id}">Product Detail</a><br/>
    </div>
</c:forEach>
</body>
</html>