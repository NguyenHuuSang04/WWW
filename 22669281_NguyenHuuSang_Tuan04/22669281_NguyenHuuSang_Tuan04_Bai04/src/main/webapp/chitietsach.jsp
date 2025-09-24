<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/15/2025
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Detail - IUH BOOKSTORE</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body { background: #f9f9f9; font-family: Arial, sans-serif; }
        .header-bar {
            background: #b3a074;
            background: linear-gradient(to bottom, #b3a074 60%, #e4d5b7 100%);
            padding: 0;
            margin-bottom: 0;
        }
        .navbar-nav .nav-link {
            color: #fff !important;
            font-weight: bold;
            padding: 18px 22px;
        }
        .navbar-nav .nav-link.active {
            background: #a18a5b !important;
            border-radius: 4px;
        }
        .main-content-flex {
            display: flex;
            flex-direction: row;
            align-items: flex-start;
            width: 100vw;
            max-width: 100vw;
        }
        .sidebar-wrap {
            flex: 0 0 280px;
            max-width: 320px;
            min-width: 220px;
        }
        .sidebar {
            background: #fff;
            border: 1px solid #ccc;
            border-radius: 6px;
            padding: 18px 16px;
            margin-top: 18px;
            min-width: 220px;
            max-width: 320px;
        }
        .sidebar-title {
            font-size: 18px;
            font-weight: bold;
            color: #444;
        }
        .sidebar-link {
            font-size: 13px; color: #555;
        }
        .search-box {
            border: 1px solid #aaa;
            border-radius: 4px;
            width: 100%;
            padding: 5px 10px;
        }
        .detail-area {
            flex: 1 1 0;
            min-width: 0;
            padding: 32px 48px 0 48px;
        }
        .detail-container {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 3px 8px rgba(0,0,0,0.03);
            padding: 32px 38px 25px 38px;
            margin: 30px 0 0 0;
            display: flex;
            align-items: flex-start;
        }
        .book-img {
            width: 180px;
            height: 260px;
            border-radius: 7px;
            object-fit: cover;
            margin-right: 36px;
            border: 1.5px solid #ccc;
            background: #eee;
            box-shadow: 0 2px 7px rgba(0,0,0,0.05);
        }
        .book-info h3 {
            margin-top: 0;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 7px;
            color: #50260e;
        }
        .book-meta {
            font-size: 16px;
            margin-bottom: 6px;
            color: #444;
        }
        .book-desc {
            font-size: 15px;
            color: #444;
            margin-bottom: 16px;
            margin-top: 14px;
            background: #f6f4ed;
            border-radius: 6px;
            padding: 13px 16px;
            border: 1px solid #e1dbc8;
        }
        .btn-back {
            margin-top: 18px;
            font-size: 15px;
            color: #1976d2;
            text-decoration: underline;
            background: none;
            border: none;
            cursor: pointer;
            padding: 0;
        }
        .btn-back:hover { color: #0d395f; text-decoration: underline; }
        @media (max-width: 1100px) {
            .main-content-flex { flex-direction: column; }
            .sidebar-wrap { max-width: 100vw; min-width: 0; }
            .detail-area { padding: 0 4vw; }
        }
        @media (max-width: 900px) {
            .detail-container { flex-direction: column; align-items: center; padding: 18px; }
            .book-img { margin-right: 0; margin-bottom: 17px; }
            .book-info { text-align: center; }
            .detail-area { padding: 0 2vw; }
            .sidebar { margin: 0 auto 16px auto; }
        }
    </style>
</head>
<body>
<!-- Header & Menu -->
<div class="header-bar">
    <div class="container-fluid">
        <div class="row align-items-center" style="padding-top: 8px;">
            <div class="col-md-4">
                <h2 style="color:#fff;font-family:serif;font-weight:bold;letter-spacing:2px;margin-bottom:0;">
                    IUH BOOKSTORE
                </h2>
            </div>
            <div class="col-md-8">
                <ul class="navbar-nav flex-row justify-content-end">
                    <li class="nav-item"><a class="nav-link" href="#">HOME</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">EXAMPLES</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">SERVICES</a></li>
                    <li class="nav-item"><a class="nav-link active" href="#">PRODUCTS</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">CONTACT</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- Main content -->
<div class="main-content-flex">
    <div class="sidebar-wrap">
        <div class="sidebar">
            <div class="sidebar-title">ABOUT US</div>
            <div style="font-size:12px; color:#444; margin-bottom:18px;">
                About us information will be here...
                <a href="#" class="sidebar-link">Read More »</a>
            </div>
            <div class="sidebar-title" style="margin-top:14px;">SEARCH SITE</div>
            <form method="get" action="books">
                <input type="text" class="search-box" name="q" placeholder=""/>
            </form>
        </div>
    </div>
    <div class="detail-area">
        <c:if test="${not empty book}">
            <div class="detail-container">
                <img class="book-img" src="${pageContext.request.contextPath}/images/${book.image}" alt="${book.title}" />
                <div class="book-info">
                    <h3>${book.title}</h3>
                    <div class="book-meta"><b>Tác giả:</b> ${book.author}</div>
                    <div class="book-meta"><b>Số lượng:</b> ${book.quantity}</div>
                    <div class="book-meta"><b>Giá:</b> <fmt:formatNumber value="${book.price}" type="number" maxFractionDigits="0"/> VNĐ</div>
                    <div class="book-desc">${book.description}</div>
                    <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${book.id}"/>
                        <input type="hidden" name="action" value="add"/>
                        <input type="number" name="quantity" value="1" min="1" style="width:50px;"/>
                        <button type="submit" class="btn btn-primary btn-sm">Add to cart</button>
                    </form>
                    <br>
                    <a href="books" class="btn-back">← Back to Product List</a>
                </div>
            </div>
        </c:if>
        <c:if test="${empty book}">
            <div class="alert alert-warning mt-4">Sách không tồn tại hoặc đã bị xóa.</div>
        </c:if>
    </div>
</div>
</body>
</html>