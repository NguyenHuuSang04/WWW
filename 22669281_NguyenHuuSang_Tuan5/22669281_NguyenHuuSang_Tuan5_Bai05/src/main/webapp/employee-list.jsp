<%@ page import="java.util.List" %>
<%@ page import="iuh.fit.nguyenhuusang_tuan04_bai05.model.Employee" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employees List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f8f9fb;
            font-family: 'Segoe UI', Arial, sans-serif;
        }
        .container {
            margin-top: 32px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08);
            padding-bottom: 30px;
        }
        .banner-img {
            width: 100%;
            border-radius: 10px 10px 0 0;
            object-fit: cover;
            margin-bottom: 14px;
        }
        h2 {
            color: #5839a5;
            margin-top: 18px;
            font-weight: 700;
        }
        .add-link {
            color: #3b82f6;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 12px;
            display: inline-block;
        }
        .add-link:hover {
            color: #4f46e5;
            text-decoration: underline;
        }
        .table th, .table td {
            vertical-align: middle !important;
        }
        .action-link {
            color: #1976d2;
            font-weight: 500;
            margin-right: 12px;
            text-decoration: none;
        }
        .action-link:hover {
            color: #b91c1c;
            text-decoration: underline;
        }
        .dept-link {
            font-size: 15px;
            color: #6d28d9;
            font-weight: 500;
        }
        .dept-link:hover {
            color: #312e81;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container shadow">
    <img class="banner-img" src="${pageContext.request.contextPath}/images/HRbanner.jpg" alt="HR Banner" height="180"/>
    <div class="d-flex justify-content-between align-items-center mt-2 mb-2">
        <h2 class="mb-0">Employees List</h2>
        <c:choose>
            <c:when test="${not empty param.deptId}">
                <a class="add-link"
                   href="${pageContext.request.contextPath}/employees?action=new&deptId=${param.deptId}">+ Add Employee</a>
            </c:when>
            <c:otherwise>
                <a class="add-link"
                   href="${pageContext.request.contextPath}/employees?action=new">+ Add Employee</a>
            </c:otherwise>
        </c:choose>
    </div>
    <table class="table table-bordered table-hover table-primary align-middle">
        <thead class="table-light">
        <tr>
            <th style="width:7%">ID</th>
            <th style="width:32%">Name Employee</th>
            <th style="width:18%">Salary</th>
            <th style="width:15%">Dept</th>
            <th style="width:18%">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.salary}</td>
                <td>
                    <c:out value="${emp.department != null ? emp.department.name : '-'}"/>
                </td>
                <td>
                    <a class="action-link" href="${pageContext.request.contextPath}/employees?action=edit&id=${emp.id}">Edit</a>
                    <a class="action-link" href="${pageContext.request.contextPath}/employees?action=delete&id=${emp.id}" onclick="return confirm('Delete employee: ${emp.name}?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="dept-link" href="${pageContext.request.contextPath}/departments">← Department</a>
</div>
</body>
</html>