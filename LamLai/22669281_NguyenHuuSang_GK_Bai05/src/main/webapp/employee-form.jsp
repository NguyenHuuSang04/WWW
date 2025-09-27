<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/25/2025
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Information</title>
</head>
<body>
    <div>
        <form action="${pageContext.request.contextPath}/employees" method="post">
            <input type="hidden" name="id" value="${employee.id}">
            Name: <input type="text" name="name" value="${employee.name}"> <br>
            Salary: <input type="text" name="salary" value="${employee.salary}"> <br>
<%--            Department:--%>
<%--            <select name="departmentId">--%>
<%--                <c:forEach var = "dept" items="${departments}">--%>
<%--                    <option value="${dept.id}"--%>
<%--                            <c:if test="${employee != null && dept.id == employee.departments.id}">selected</c:if>--%>
<%--                    >${dept.name}</option>--%>

<%--                </c:forEach>--%>

<%--            </select>--%>

            <c:if test="${fixDept}">
                <input type="hidden" name="departmentId" value="${selectedDept.id}">
                Department:
                <select name="departmentId" disabled>
                    <option value="${selectedDept.id}" selected> ${selectedDept.name}</option>
                </select>
            </c:if>

            <c:if test="${!fixDept}">
                Department:
                <select name="departmentId">
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.id}"
                                <c:if test="${employee != null && dept.id == employee.departments.id}">selected</c:if>
                        >${dept.name}</option>

                    </c:forEach>
                </select>
            </c:if>

            <br>

            <input type="submit" value="Save">

        </form>
    </div>
</body>
</html>
