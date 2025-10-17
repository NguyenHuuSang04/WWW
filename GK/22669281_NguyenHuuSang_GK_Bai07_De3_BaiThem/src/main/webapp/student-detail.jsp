<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/28/2025
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student detail</title>
</head>
<body>

<table>
    <tr>
        <th>MSSV</th>
        <th>Họ tên</th>
        <th>Email</th>
        <th>Số điện thoại</th>
        <th>Lớp</th>
        <th>Ngành</th>
        <th>Năm nhập học</th>
        <th>Trạng thái</th>
        <th>Ngày nhập học</th>
        <th>Hành động</th>
    </tr>
    <tr>
        <td>${student.studentID}</td>
        <td>${student.fullName}</td>
        <td>${student.email}</td>
        <td>${student.phone}</td>
        <td>${student.className}</td>
        <td>${student.major}</td>
        <td>${student.enrollment_year}</td>
        <td>${student.status}</td>
        <td>${student.createDate}</td>
        <td>
<%--            <a href="${pageContext.request.contextPath}/scores?action=new&studentId=${student.studentID}">Nhập--%>
<%--                điểm mới</a>--%>
            <a href="${pageContext.request.contextPath}/scores?action=list&studentId=${student.studentID}">Xem
                bảng điểm</a>
        </td>
    </tr>


</table> <br>

<table>
    <tr>
        <th>Môn học</th>
        <th>Điểm</th>
        <th>Loại điểm</th>
    </tr>

    <c:forEach items="${scores}" var="item">
        <tr>
            <td>${item.subjectName}</td>
            <td>${item.score}</td>
            <td>${item.scoreType}</td>
        </tr>
    </c:forEach>



</table>

</body>
</html>

<%--// bảng điểm môn đã học, "nhập điểm mới", "xem bảng điểm"--%>