<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/8/2025
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<html>
<head>
    <title>Student Result</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f6f7; }
        .container { width: 800px; margin: 30px auto; padding: 20px; background: #fff; border: 1px solid #ccc; border-radius: 8px; }
        h2, h3 { color: #333; }
    </style>
</head>
<body>
<div class="container">
    <h2>Student Information</h2>
    <p><b>Name:</b> ${student.firstName} ${student.lastName}</p>
    <p><b>Date of Birth:</b> ${student.dateOfBirth}</p>
    <p><b>Email:</b> ${student.email}</p>
    <p><b>Mobile:</b> ${student.mobileNumber}</p>
    <p><b>Gender:</b>
        <c:choose>
            <c:when test="${student.gender}">Male</c:when>
            <c:otherwise>Female</c:otherwise>
        </c:choose>
    </p>
    <p><b>Address:</b> ${student.address}, ${student.city}, ${student.state}, ${student.country}</p>
    <p><b>Course:</b> ${student.course}</p>

    <h3>Hobbies:</h3>
    <ul>
        <c:forEach var="h" items="${student.hobbies}">
            <li>${h}</li>
        </c:forEach>
    </ul>

    <h3>Qualifications:</h3>
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>Examination</th>
            <th>Board</th>
            <th>Percentage</th>
            <th>Year of Passing</th>
        </tr>
        <c:forEach var="q" items="${student.qualifications}">
            <tr>
                <td>${q.examination}</td>
                <td>${q.board}</td>
                <td>${q.percentage}</td>
                <td>${q.yearOfPassing}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>