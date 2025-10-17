<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/28/2025
  Time: 9:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<table>
    <tr>
        <th>MSSV</th>
        <th>Họ tên</th>
        <th>Lớp</th>
        <th>Ngành</th>
        <th>Năm nhập học</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>

    <c:forEach items="${students}" var="item">
        <tr>
            <td>${item.studentID}</td>
            <td>${item.fullName}</td>
            <td>${item.major}</td>
            <td>${item.className}</td>
            <td>${item.enrollment_year}</td>
            <td>${item.status}</td>
            <td>
                <a href="${pageContext.request.contextPath}/students?action=viewbyid&studentId=${item.studentID}">Xem
                    chi tiết</a>
                <a href="${pageContext.request.contextPath}/scores?action=new&studentId=${item.studentID}">Nhập điểm
                    mới</a>
            </td>
        </tr>

    </c:forEach>
    <tr>
        <th>
            <a href="${pageContext.request.contextPath}/students?action=new">thêm sinh viên</a>
        </th>
    </tr>
</table>

</body>
</html>
