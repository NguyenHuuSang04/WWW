<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/29/2025
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/accounts">
    <select name="criteria">
        <option value="name" ${param.criteria == 'name' ? 'selected' : ''}>Theo tên</option>
        <option value="address" ${param.criteria == 'address' ? 'selected' : ''}>Theo địa chỉ</option>
    </select>
    <input type="text" name="keyword" value="${param.keyword != null ? param.keyword : ''}" placeholder="Nhập từ khóa"/>
    <button type="submit" name="action" value="search">Search</button>
    <button type="submit" name="action" value="list">Reset</button>
</form>

<table>
    <tr>
        <th>Account Number</th>
        <th>Ownen_Name</th>
        <th>Card_number</th>
        <th>OwnerAddress</th>
        <th>Amount</th>
    </tr>

    <c:forEach items="${accounts}" var="ac">
        <tr>
            <td>${ac.account_number}</td>
            <td>${ac.owner_name}</td>
            <td>${ac.card_number}</td>
            <td>${ac.owner_address}</td>
            <td>${ac.amount}</td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
