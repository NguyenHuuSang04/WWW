<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Departments List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
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
        .search-bar {
            margin-bottom: 18px;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .search-bar input[type=text] {
            padding: 5px 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 15px;
            width: 240px;
        }
        .search-bar button {
            padding: 6px 18px;
            border-radius: 6px;
            border: none;
            background: #3b82f6;
            color: #fff;
            font-weight: 600;
        }
        .search-bar button:hover {
            background: #2563eb;
        }
    </style>
</head>
<body>
<div class="container shadow">
    <img class="banner-img" src="${pageContext.request.contextPath}/images/HRbanner.jpg" alt="HR Banner" height="180"/>
    <div class="d-flex justify-content-between align-items-center mt-2 mb-2">
        <h2 class="mb-0">Departments List</h2>
        <a class="add-link" href="${pageContext.request.contextPath}/departments?action=new">Add Department</a>
    </div>
    <form class="search-bar" action="${pageContext.request.contextPath}/departments" method="get">
        <label for="searchName" style="margin:0; font-weight:500;color:#333;">Tìm phòng ban:</label>
        <input id="searchName" type="text" name="q" value="${param.q != null ? param.q : ''}" placeholder="Nhập tên phòng ban..."/>
        <button type="submit">Search</button>
    </form>
    <table class="table table-bordered table-hover table-primary align-middle">
        <thead class="table-light">
        <tr>
            <th style="width:10%;">DEPT ID</th>
            <th style="width:40%;">Name Department</th>
            <th style="width:25%;">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dept" items="${departments}">
            <tr>
                <td>${dept.id}</td>
                <td>${dept.name}</td>
                <td>
                    <a class="action-link" href="${pageContext.request.contextPath}/departments?action=edit&id=${dept.id}">Edit</a>
                    <a class="action-link" href="${pageContext.request.contextPath}/departments?action=delete&id=${dept.id}" onclick="return confirm('Delete department: ${dept.name}?');">Delete</a>
                    <a class="action-link" href="${pageContext.request.contextPath}/employees?deptId=${dept.id}">Employees</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>