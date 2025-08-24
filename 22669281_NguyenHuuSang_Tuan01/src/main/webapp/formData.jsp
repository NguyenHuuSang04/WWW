<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 8/18/2025
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Register</h2>
<form action="${pageContext.request.contextPath}/processFormUpload" method="post">
    <!-- TextBox -->
    Name: <input type="text" name="firstName" placeholder="First"/>
    <input type="text" name="lastName" placeholder="Last"/> <br><br>

    Username: <input type="text" name="username"/> <br><br>

    E-mail: <input type="email" name="email"/> <br><br>

    Password: <input type="password" name="password"/> <br><br>

    Facebook: <input type="text" name="facebook" placeholder="Facebook URL"/> <br><br>

    <!-- ComboBox -->
    Gender:
    <select name="gender">
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
    </select><br><br>

    <!-- CheckBox -->
    Interests: <br>
    <input type="checkbox" name="interest" value="Coding"> Coding
    <input type="checkbox" name="interest" value="Gaming"> Gaming
    <input type="checkbox" name="interest" value="Reading"> Reading <br><br>

    <!-- TextArea -->
    Short Bio:<br>
    <textarea name="bio" rows="4" cols="40"></textarea><br><br>

    <input type="submit" value="Submit"/>
</form>
</body>
</html>
