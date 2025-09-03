<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 8/25/2025
  Time: 8:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bài 5: login</title>
</head>
<body>
<h2>Đăng nhập</h2> <br/>
<form action="${pageContext.request.contextPath}/login" method="post">
   Tên đăng nhập: <input type="text" name="username" placeholder="username"> <br>

    Mật khẩu: <input type="password" name="password" placeholder="password"> <br>

    <button type="submit">Submit</button>
</form>


<%--code jave jsp nằm trong cập <% %>--%>
<%--lấy attribute có tên "error" được set từ Servlet--%>
<%--req.getAttribute trả về Object, nên phaair ép kiểu String để gắn vào biến error--%>

<%String error = (String) request.getAttribute("error");
    if (error != null) {
%> <p style="color:red;"> <%=error%> </p> <%
    }
%>
</body>
</html>
