<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/25/2025
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Book Detail</h2>
    <c:if test="${not empty book}">
        <ul>
            <li>Title: ${book.title}</li>
            <li>Author: ${book.author}</li>
            <li>Quantity: ${book.quantity}</li>
            <li>Price: ${book.price}</li>
            <li>Description: ${book.description}</li>
            <img src="${pageContext.request.contextPath}/images/${book.imgurl}">
        </ul>

    </c:if>

<p>
    <a href="${pageContext.request.contextPath}/books">Back to Book List</a>
</p>
</body>
</html>
