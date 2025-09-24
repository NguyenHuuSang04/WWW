<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/15/2025
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>YOUR SHOPPING CART</title>
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
        .cart-area {
            flex: 1 1 0;
            padding: 32px 48px 0 48px;
            min-width: 0;
        }
        .cart-table {
            margin-top: 10px;
            width: 100%;
        }
        .cart-table th, .cart-table td {
            border: 1px solid #888;
            padding: 7px 8px;
            text-align: center;
        }
        .cart-table th {
            background: #f2f2f2;
        }
        .empty { color: #888; margin: 23px 0 23px 0;}
        .btn-action { padding: 2px 12px; font-size: 14px;}
        .cart-summary { margin: 15px 0 0 0; text-align: right; }
        .btn-bar { margin-top: 12px; }
        @media (max-width: 1100px) {
            .main-content-flex { flex-direction: column; }
            .sidebar-wrap { max-width: 100vw; min-width: 0; }
            .cart-area { padding: 0 4vw; }
        }
        @media (max-width: 900px) {
            .cart-area { padding: 0 2vw; }
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
            <div style="margin-top:18px;">
                <b>Shopping cart</b> (<c:out value="${not empty cart.items ? cart.items.size() : 0}"/>)
            </div>
        </div>
    </div>
    <div class="cart-area">
        <h4 style="margin-bottom:18px;">YOUR SHOPPING CART</h4>
        <c:choose>
            <c:when test="${empty cart.items}">
                <p class="empty">Cart is empty!</p>
            </c:when>
            <c:otherwise>
                <table class="cart-table">
                    <tr>
                        <th>Product ID</th>
                        <th>Product name</th>
                        <th>Price</th>
                        <th>Qty</th>
                        <th>Total</th>
                        <th>Remove</th>
                    </tr>
                    <c:forEach var="item" items="${cart.items}">
                        <tr>
                            <td>${item.book.id}</td>
                            <td>${item.book.title} - Tác giả: ${item.book.author}</td>
                            <td><fmt:formatNumber value="${item.book.price}" type="number" maxFractionDigits="0"/></td>
                            <td>
                                <form action="cart" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="update"/>
                                    <input type="hidden" name="bookId" value="${item.book.id}"/>
                                    <input type="number" name="quantity" value="${item.quantity}" min="1" style="width:48px;"/>
                                    <button type="submit" class="btn btn-secondary btn-action">Update</button>
                                </form>
                            </td>
                            <td><fmt:formatNumber value="${item.book.price * item.quantity}" type="number" maxFractionDigits="0"/></td>
                            <td>
                                <form action="cart" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="remove"/>
                                    <input type="hidden" name="bookId" value="${item.book.id}"/>
                                    <button type="submit" class="btn btn-danger btn-action">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4" class="cart-summary"><b>Total price</b></td>
                        <td colspan="2">
                            <b>
                                <fmt:formatNumber value="${cart.totalPrice}" type="number" maxFractionDigits="0"/>
                                (VNĐ)
                            </b>
                        </td>
                    </tr>
                </table>
                <div class="btn-bar">
                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="clear"/>
                        <button type="submit" class="btn btn-warning">Clear Cart</button>
                    </form>
                    <a href="books" class="btn btn-secondary">Continue shopping</a>
                    <a href="thanhtoan.jsp" class="btn btn-success">Checkout</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>