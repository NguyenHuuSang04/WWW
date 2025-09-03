<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 8/25/2025
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form gửi mail</title>
</head>
<body>
<h2>Form gửi Email</h2>
<form action="${pageContext.request.contextPath}/sendMail"
      method="post"
      enctype="multipart/form-data">

    <label for="to">Người nhận:</label><br>
    <input type="email" id="to" name="to" required placeholder="example@gmail.com"/><br><br>

    <label for="subject">Tiêu đề:</label><br>
    <input type="text" id="subject" name="subject" required/><br><br>

    <label for="content">Nội dung:</label><br>
    <textarea id="content" name="content" rows="6" cols="50" required></textarea><br><br>

    <label for="file">Đính kèm file:</label><br>
    <input type="file" id="file" name="file"/><br><br>

    <button type="submit">Gửi Mail</button>
    <button type="reset">Reset</button>
</form>
</body>
</html>
