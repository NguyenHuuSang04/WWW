<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/3/2025
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload to Database</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/uploadtoDadabase" method="post" enctype="multipart/form-data">
    <h1>File Upload to Database</h1>
    <h2>( multipart/form-data )</h2>
    First Name: <input type="text" name="firstName"> <br>
    Last Name: <input type="text" name="lastName"> <br>
    Portrait Photo: <input type="file" name="photo" id=""><br>
    <input type="submit" value="Save">
</form>
</body>
</html>
