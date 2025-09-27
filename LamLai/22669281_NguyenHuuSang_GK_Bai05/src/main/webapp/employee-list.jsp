<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/25/2025
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
    <div>
        <h2>Employee List</h2>
        <c:choose>
            <c:when test="${not empty param.deptId}">
                <a href="${pageContext.request.contextPath}/employees?action=new&deptId=${param.deptId}">Add Employee</a> <br>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/employees?action=new">Add Employee</a> <br>
            </c:otherwise>
        </c:choose>





        <table class="table table-primary">
            <tr>
                <th>ID</th>
                <th>Name Employee</th>
                <th>Salary</th>
                <th>Dept</th>
                <th>Action</th>
            </tr>

            <c:forEach var="emp" items="${employees}">
                <tr>
                    <th>${emp.id}</th>
                    <th>${emp.name}</th>
                    <th>${emp.salary}</th>
                    <th>${emp.departments.name}</th>
                    <th>
                        <a href="${pageContext.request.contextPath}/employees?action=edit&id=${emp.id}">Edit</a> |
                        <a href="${pageContext.request.contextPath}/employees?action=delete&id=${emp.id}">Delete</a>
                    </th>
                </tr>
            </c:forEach>
        </table>

        <a href="${pageContext.request.contextPath}/departments">Department</a>

    </div>
</body>
</html>
