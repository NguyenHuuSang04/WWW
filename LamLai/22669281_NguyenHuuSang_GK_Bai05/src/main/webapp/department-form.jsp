<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/25/2025
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <form action="${pageContext.request.contextPath}/departments" method="post">
            <input type="hidden" name="id" value="${department.id}">
            Name: <input type="text" name="name" value="${department.name}"> <br>
            <input type="submit" value="Save">


        </form>
    </div>
</body>
</html>
