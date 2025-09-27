<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/24/2025
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product - detail</title>
</head>
<body>
    <h2>Product detail</h2>
<c:if test="${not empty product}">
    <ul>
        <li>Id: ${product.id}</li>
        <li>Model: ${product.model}</li>
        <li>Description: ${product.description}</li>
        <li>Quantity: ${product.quantity}</li>
        <li>Price: ${product.price}</li>
        <img src="${pageContext.request.contextPath}/images/${product.imgurl}" alt ="${product.model}" width = "150">
    </ul>

</c:if>
<p>
    <a href="${pageContext.request.contextPath}/product">Back to ProductList</a>
</p>

</body>
</html>
