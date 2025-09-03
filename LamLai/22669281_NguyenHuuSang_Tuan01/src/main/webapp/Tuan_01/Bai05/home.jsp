<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 8/25/2025
  Time: 8:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h2>Xin chào, ${sessionScope.user}</h2>
<%--chuyển đến trang secure.jsp--%>
<a href="${pageContext.request.contextPath}/Tuan_01/Bai05/secure/secure.jsp">Trang bảo mật</a>

<%--// mappiong với 1 Servlet khác--%>
<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</body>
</html>
