<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/24/2025
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<div class="container">
    <h2>Cart</h2>
    <c:if test="${empty cart.items}">
        <p>Cart is emppty!</p>
    </c:if>

    <c:if test="${not empty cart.items}">
        <table class="table table-border">
            <tr>
                <th>Model</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>

            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>${item.product.model}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                            <input type="hidden" name="action"
                                   value="update"/> <%--   gửi tham số action = update để Servlet biết đây là thao taác update --%>
                            <input type="hidden" name="productId"
                                   value="${item.product.id}"/> <%-- gửi id của product cần update --%>
                            <input type="number" name="quantity" value="${item.quantity}" min="1"/>
                            <input type="submit" value="Update"/>
                        </form>
                    </td>

                    <td>${item.product.price}</td>
                    <td>${item.product.price * item.quantity}</td>

                    <td>
                        <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="remove"/>
                            <input type="hidden" name="productId" value="${item.product.id}"/>
                            <input type="submit" value="Remove"/>
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <p><strong>Total:</strong>${cart.total}</p>
    </c:if>

    <a href="product">Continue Shopping</a>


</div>

</body>
</html>
