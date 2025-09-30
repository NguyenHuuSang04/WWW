<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/28/2025
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Score detail</title>
</head>


<body>
<table>
    <tr>
        <th>Môn học</th>
        <th>Điểm</th>
        <th>Học kỳ</th>
        <th>Ngày thi</th>
        <th>Loại điểm</th>
        <th>Ghi chú</th>
        <th>Sửa điểm</th>

    </tr>

    <c:forEach items="${scores}" var="item">
        <tr>
            <td>${item.subjectName}</td>
            <td>${item.score}</td>
            <td>${item.semester}</td>
            <td>${item.exemDate}</td>
            <td>${item.scoreType}</td>
            <td>${item.notes}</td>
            <td>
<%--                <a href="${pageContext.request.contextPath}/scores?action=edit&scoreId=${item.scoreId}">Sửa điểm</a>--%>
    <a href="${pageContext.request.contextPath}/scores?action=edit&scoreId=${item.scoreId}&studentId=${item.student.studentID}">Sửa điểm</a>
            </td>
        </tr>

    </c:forEach>
    <tr>
        <td><b>GPA: </b>${gpa}</td>
    </tr>
</table>
</body>
</html>
