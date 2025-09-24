<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/23/2025
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QUẢN LÝ TIN TỨC</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header-bar">
    <h2>QUẢN LÝ TIN TỨC</h2>
    <nav>
        <a href="${pageContext.request.contextPath}/tintuc">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/tin-tuc-form">Thêm tin mới</a>
        <a href="${pageContext.request.contextPath}/quan-ly-tin-tuc">Quản lý</a>
    </nav>
    <hr>
</div>
<div class="container">