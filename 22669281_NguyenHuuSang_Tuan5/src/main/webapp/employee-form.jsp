<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="iuh.fit.nguyenhuusang_tuan04_bai05.model.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Information</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background: #f8f9fb;
        }
        .container {
            margin-top: 38px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.09);
            padding-bottom: 30px;
            max-width: 520px;
        }
        .banner-img {
            width: 100%;
            border-radius: 10px 10px 0 0;
            object-fit: cover;
            margin-bottom: 18px;
        }
        h2 {
            color: #5839a5;
            margin-top: 18px;
            font-weight: 700;
            text-align: center;
            margin-bottom: 25px;
        }
        .form-label {
            font-weight: 500;
            color: #3f366b;
        }
        .form-control, .form-select {
            border-radius: 7px;
            font-size: 16px;
        }
        .btn-save {
            background: #3b82f6;
            color: white;
            border-radius: 7px;
            padding: 8px 34px;
            font-weight: bold;
            margin-top: 12px;
            letter-spacing: 1px;
        }
        .btn-save:hover {
            background: #2563eb;
        }
    </style>
</head>
<body>
<div class="container shadow">
    <img class="banner-img" src="${pageContext.request.contextPath}/images/HRbanner.jpg" alt="HR Banner" height="180"/>
    <h2>Employee Information</h2>
    <form action="${pageContext.request.contextPath}/employees" method="post">
        <input type="hidden" name="id" value="${employee != null ? employee.id : ''}"/>
        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input id="name" type="text" class="form-control" name="name" value="${employee != null ? employee.name : ''}" required/>
        </div>
        <div class="mb-3">
            <label for="salary" class="form-label">Salary:</label>
            <input id="salary" type="number" min="0" step="0.01" class="form-control" name="salary" value="${employee != null ? employee.salary : ''}" required/>
        </div>
        <div class="mb-3">
            <label for="departmentId" class="form-label">Department:</label>
            <select id="departmentId" name="departmentId" class="form-select" required>
                <option value="">-- Select Department --</option>
                <c:forEach var="dep" items="${departments}">
                    <option value="${dep.id}"
                            <c:if test="${employee != null && employee.department != null && employee.department.id == dep.id}">selected</c:if>
                    >${dep.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-save">Save</button>
        </div>
    </form>
</div>
</body>
</html>