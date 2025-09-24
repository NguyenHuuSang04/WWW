<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/23/2025
  Time: 6:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="includes/header.jsp"/>
<h2 style="color:#2156a4;">Quản lý & Xoá tin tức</h2>

<c:if test="${not empty msg}">
    <p style="color:green;font-weight:bold;">${msg}</p>
</c:if>
<c:if test="${not empty errorMsg}">
    <p style="color:red;font-weight:bold;">${errorMsg}</p>
</c:if>

<table class="table-news">
    <thead>
    <tr>
        <th>Mã TT</th>
        <th>Tiêu đề</th>
        <th>Liên kết</th>
        <th>Nội dung</th>
        <th>Danh mục</th>
        <th>Xoá</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tt" items="${tinTucs}">
        <tr>
            <td>${tt.maTT}</td>
            <td>${tt.tieuDe}</td>
            <td>
                <a class="btn-view" href="${tt.lienKet}" target="_blank">Xem</a>
            </td>
            <td>
                <c:choose>
                    <c:when test="${fn:length(tt.noiDungTT) > 60}">
                        ${fn:substring(tt.noiDungTT, 0, 60)}...
                    </c:when>
                    <c:otherwise>
                        ${tt.noiDungTT}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:forEach var="dm" items="${danhMucs}">
                    <c:if test="${dm.maDM eq tt.maDM}">
                        ${dm.tenDanhMuc}
                    </c:if>
                </c:forEach>
            </td>
            <td>
                <form action="quan-ly-tin-tuc" method="post" style="margin:0;">
                    <input type="hidden" name="deleteMaTT" value="${tt.maTT}"/>
                    <button type="submit" class="btn-view"
                            onclick="return confirm('Xoá tin tức này?');">Xoá</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty tinTucs}">
        <tr>
            <td colspan="6" style="color:#b00;text-align:center;">Không có tin tức nào.</td>
        </tr>
    </c:if>
    </tbody>
</table>
<jsp:include page="includes/footer.jsp"/>