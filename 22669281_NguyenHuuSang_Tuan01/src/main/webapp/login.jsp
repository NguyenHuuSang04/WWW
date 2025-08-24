<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 8/18/2025
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Đăng nhập</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    Tên đăng nhập: <input type="text" name="username"/><br>
    Mật khẩu: <input type="password" name="password"/><br>
    <input type="submit" value="Đăng nhập"/>
</form>

<% String error = (String) request.getAttribute("error");
    if (error != null) { %>
<p style="color:red;"><%= error %>
</p>
<% } %>
</body>
</html>
