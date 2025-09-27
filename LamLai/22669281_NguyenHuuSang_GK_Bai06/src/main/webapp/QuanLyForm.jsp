<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/26/2025
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <th>Tiêu đề</th>
            <th>Nội dung</th>
            <th>Liên kết</th>
            <th>Tên danh mục</th>
            <th>Action</th>

        </tr>
        <c:forEach items="${tinTucs}" var="item">
            <tr>
                <th>${item.tieuDe}</th>
                <th>${item.noiDungTT}</th>
                <th>${item.lienKet}</th>
                <th>${item.danhMuc.tenDM}</th>
                <th>
                    <a href="${pageContext.request.contextPath}/tintucform?action=edit&maTT=${item.maTT}">Edit</a>
                    <a href="${pageContext.request.contextPath}/quanlyform?action=remove&maTT=${item.maTT}">Delete</a>
                </th>
            </tr>

        </c:forEach>
    </table>
</body>
</html>
