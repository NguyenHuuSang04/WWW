<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 8/25/2025
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload to Database</title>
</head>
<body>
<h2 style="text-align: center;">File Upload to Database<br/>(multipart/form-data)</h2>
<form action="${pageContext.request.contextPath}/uploadToDB"
      method="post"
      enctype="multipart/form-data"
      style="width: 400px; margin: auto; border: 1px solid #ccc; padding: 15px;">

    <label for="firstName">First Name:</label><br/>
    <input type="text" id="firstName" name="firstName" required/><br/><br/>

    <label for="lastName">Last Name:</label><br/>
    <input type="text" id="lastName" name="lastName" required/><br/><br/>

    <label for="photo">Portrait Photo:</label><br/>
    <input type="file" id="photo" name="photo" accept="image/*"/><br/><br/>

    <input type="submit" value="Save"/>
</form>
</body>
</html>
