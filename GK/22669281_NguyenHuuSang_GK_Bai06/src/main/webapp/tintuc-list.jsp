<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/26/2025
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/tintucform?action=new">Thêm tin tức</a>
<a href="${pageContext.request.contextPath}/quanlyform">Quản lý tin tức</a>

<form method="get" action="${pageContext.request.contextPath}/tintucs">
    <input type="hidden" name="action" value="listByDanhMuc"/>
    <label for="idDM">Lọc theo danh mục:</label>
    <select name="idDM" id="idDM" onchange="this.form.submit()">
        <option value="">Tất cả</option>
        <c:forEach items="${danhmucs}" var="dm">
            <option value="${dm.maDM}" <c:if test="${param.idDM == dm.maDM}">selected</c:if>>${dm.tenDM}</option>
        </c:forEach>
    </select>
</form>

<table class="table table-primary">
    <tr>
        <th>Mã Tin tức</th>
        <th>Tiêu đề</th>
        <th>Nội dung tin tức</th>
        <th>Liên kết</th>
        <th>Tên danh mục</th>
        <th>Hành động</th>
    </tr>


    <c:forEach items="${tintucs}" var="item">
        <tr>
            <th>${item.maTT}</th>
            <th>${item.tieuDe}</th>
            <th>${item.noiDungTT}</th>
            <th>${item.lienKet}</th>
            <th>${item.danhMuc.tenDM}</th>
            <th>
                <a>Eidt</a>
                <a>Delete</a>
            </th>
        </tr>
    </c:forEach>

</table>
</body>
</html>
