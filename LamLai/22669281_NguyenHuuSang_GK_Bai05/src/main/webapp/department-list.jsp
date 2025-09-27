<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/25/2025
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <h2>Department List</h2>
        <a href="${pageContext.request.contextPath}/departments?action=new">Add department</a>
        Tìm phòng ban:
        <form method="get" action="${pageContext.request.contextPath}/departments">
            <input type="text" name="keyword" placeholder="Nhập tên phòng ban">
            <input type="submit" value="Tìm kiếm">
            <input type="hidden" name="action" value="search">
        </form>
        <table>
            <tr>
                <th>Id</th>
                <th>Name department</th>
                <th>Action</th>
            </tr>

            <c:forEach var="dept" items="${departments}">
                <tr>
                    <th>${dept.id}</th>
                    <th>${dept.name}</th>
                    <th>
                        <a href="${pageContext.request.contextPath}/departments?action=edit&id=${dept.id}">Edit</a>
                        <a href="${pageContext.request.contextPath}/departments?action=delete&id=${dept.id}">Delete</a>
                        <a href="${pageContext.request.contextPath}/employees?action=employees&deptId=${dept.id}">Employees</a>
                    </th>
                </tr>

            </c:forEach>
        </table>

        <a href="${pageContext.request.contextPath}/employees">Employee List</a>

    </div>

</body>
</html>
