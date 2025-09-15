<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/8/2025
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration Form</title>
    <style>
        body {
            background: #f4f6f7;
            font-family: Arial, sans-serif;
        }
        .registration-container {
            max-width: 420px;
            margin: 40px auto;
            background: #fff;
            border: 2px solid #2ecc40;
            border-radius: 10px;
            padding: 30px 28px 24px 28px;
            box-shadow: 0 0 10px rgba(44,204,64,0.1);
        }
        h2 {
            text-align: center;
            font-size: 2em;
            margin-bottom: 22px;
            font-weight: 600;
        }
        .input-row {
            display: flex;
            gap: 10px;
            margin-bottom: 18px;
        }
        .input-row input {
            flex: 1;
        }
        input[type="text"], input[type="email"], input[type="password"], select {
            width: 100%;
            padding: 12px 10px;
            margin-bottom: 0;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 15px;
        }
        .label {
            font-weight: 500;
            margin-bottom: 6px;
            display: block;
        }
        .birthday-row {
            display: flex;
            gap: 10px;
            margin-bottom: 18px;
        }
        .birthday-row select {
            flex: 1;
        }
        .gender-row {
            display: flex;
            align-items: center;
            gap: 20px;
            margin-bottom: 24px;
        }
        .gender-row label {
            margin-bottom: 0;
            font-weight: normal;
        }
        .signup-btn {
            width: 100%;
            background: #1877f2;
            color: #fff;
            border: none;
            border-radius: 6px;
            font-size: 18px;
            padding: 13px 0;
            cursor: pointer;
            font-weight: 600;
            margin-top: 8px;
            transition: background 0.2s;
        }
        .signup-btn:hover {
            background: #125dcc;
        }
    </style>
</head>
<body>
<div class="registration-container">
    <h2>User Registration Form</h2>
    <form action="${pageContext.request.contextPath}/registerUser" method="post">
        <div class="input-row">
            <input type="text" name="firstName" placeholder="First Name" required>
            <input type="text" name="lastName" placeholder="Last Name" required>
        </div>
        <input type="email" name="email" placeholder="Your Email" required style="margin-bottom:18px;">
        <input type="password" name="password" placeholder="Password" required style="margin-bottom:18px;">

        <div class="label">Birthday</div>
        <div class="birthday-row">
            <select name="birthMonth" required>
                <option value="">Month</option>
                <option value="1">Jan</option><option value="2">Feb</option>
                <option value="3">Mar</option><option value="4">Apr</option>
                <option value="5">May</option><option value="6">Jun</option>
                <option value="7">Jul</option><option value="8">Aug</option>
                <option value="9">Sep</option><option value="10">Oct</option>
                <option value="11">Nov</option><option value="12">Dec</option>
            </select>
            <select name="birthDay" required>
                <option value="">Day</option>
                <% for (int i = 1; i <= 31; i++) { %>
                <option value="<%=i%>"><%=i%></option>
                <% } %>
            </select>
            <select name="birthYear" required>
                <option value="">Year</option>
                <%
                    int thisYear = java.time.Year.now().getValue();
                    for (int y = thisYear; y >= 1900; y--) {
                %>
                <option value="<%=y%>"><%=y%></option>
                <% } %>
            </select>
        </div>

        <div class="label">Gender</div>
        <div class="gender-row">
            <label><input type="radio" name="gender" value="Female" required> Female</label>
            <label><input type="radio" name="gender" value="Male" required> Male</label>
        </div>
        <button type="submit" class="signup-btn">Sign Up</button>
    </form>
</div>
</body>
</html>