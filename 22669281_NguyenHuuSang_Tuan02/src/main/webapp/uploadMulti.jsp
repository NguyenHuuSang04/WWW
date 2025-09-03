<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 8/25/2025
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bài 6</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/uploadmulti" method="post" enctype="multipart/form-data">
    File #1: <input type="file" name="file"/> <br>
    File #2: <input type="file" name="file"/> <br>
    File #3: <input type="file" name="file"/> <br>
    File #4: <input type="file" name="file"/> <br>
    File #5: <input type="file" name="file"/> <br>
    <input type="submit" value="Upload"/>
    <input type="reset" value="Reset"/>
</form>
</body>
</html>
