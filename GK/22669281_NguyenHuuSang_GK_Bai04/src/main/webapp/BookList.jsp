<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/24/2025
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    <a href="cart">View cart</a>
</p>

<div>
    <c:forEach items="${books}" var="b">
        <div>
            <b>${b.title}</b>
            <img src="images/${b.imgurl}"> <br>
            <form action="${pageContext.request.contextPath}/cart" method="post">
                <input type="hidden"  name="id" value="${b.id}"> <br>
                <input type="number" name="price" value="${b.price}"> <br>
                <input type="number" size="2" value="1" name="quantity"> <br>
                <input type="text"  name="author" value="${b.author}"> <br>
                <input type="text" size="2" name="description" value="${b.description}"> <br>
                <input type="hidden" name="action" value="add" ><br>
                <input type="submit" name="addToCart" value="Add to Cart">
            </form>
        </div>

        <a href="${pageContext.request.contextPath}/book?id=${b.id}">Book detail</a>

    </c:forEach>
</div>
</body>
</html>
