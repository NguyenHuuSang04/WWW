<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/25/2025
  Time: 8:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <h2>Cart</h2>
        <c:if test="${empty cart.books}">
            <p>Cart is empty</p>
        </c:if>

        <c:if test="${not empty cart.books}">
            <table>
                <tr>
                    <th>BookID</th>
                    <th>Title book</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Remove</th>
                </tr>

                <c:forEach var="item" items="${cart.books}">
                    <tr>
                        <td>${item.book.id}</td>
                        <td>${item.book.title}</td>
                        <td>${item.book.price}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id" value="${item.book.id}">
                                <input type="number" name="quantity" value="${item.quantity}" min="1">
                                <input type="submit" value="Update">
                            </form>
                        </td>
                        <td>${item.book.price * item.quantity}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/cart" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="remove">
                                <input type="hidden" name="id" value="${item.book.id}">
                                <input type="submit" value="Remove">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <p> <strong>Total: </strong>${cart.total}</p>

        </c:if>
    </div>
</body>
</html>
