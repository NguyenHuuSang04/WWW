<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/5/2025
  Time: 8:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bài 8: Form send mail</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/formSendMail" method="post" enctype="multipart/form-data">
    Người nhận: <input type="text" name="to" id=""> <br>
    Tiêu đề : <input type="text" name="subject" id=""> <br>
    Nội dung: <input type="text" name="content" id=""> <br>
    File đính kèm: <input type="file" name="file" id=""> <br>
    <input type="submit" value="Gửi mail">
</form>
</body>
</html>
