<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/8/2025
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f7;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 800px;
            margin: 30px auto;
            padding: 20px;
            background: #fff;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            font-weight: bold;
        }

        input, textarea, select {
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #aaa;
            border-radius: 5px;
            font-size: 14px;
        }

        textarea {
            resize: vertical;
        }

        .radio-group, .checkbox-group {
            margin-top: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        .btn-group {
            margin-top: 20px;
            text-align: center;
        }

        input[type="submit"], input[type="reset"] {
            padding: 10px 20px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
        }

        input[type="reset"] {
            background-color: #6c757d;
            color: white;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        input[type="reset"]:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Student Registration Form</h2>
    <form action="${pageContext.request.contextPath}/registerStudent" method="post">
        <label>First Name:</label>
        <input type="text" name="firstName">

        <label>Last Name:</label>
        <input type="text" name="lastName">

        <label>Date of Birth:</label>
        <input type="date" name="dob">

        <label>Email:</label>
        <input type="email" name="email">

        <label>Mobile:</label>
        <input type="text" name="mobile">

        <label>Gender:</label>
        <div class="radio-group">
            <input type="radio" name="gender" value="Male"> Male
            <input type="radio" name="gender" value="Female"> Female
        </div>

        <label>Address:</label>
        <textarea name="address"></textarea>

        <label>City:</label>
        <input type="text" name="city">

        <label>State:</label>
        <input type="text" name="state">

        <label>Country:</label>
        <input type="text" name="country">

        <label>Hobbies:</label>
        <div class="checkbox-group">
            <input type="checkbox" name="hobbies" value="Drawing"> Drawing
            <input type="checkbox" name="hobbies" value="Singing"> Singing
            <input type="checkbox" name="hobbies" value="Dancing"> Dancing
            <input type="checkbox" name="hobbies" value="Sketching"> Sketching
            <input type="checkbox" name="hobbies" value="Others"> Others
        </div>

        <label>Qualification:</label>
        <table>
            <tr>
                <th>Sl.No</th>
                <th>Examination</th>
                <th>Board</th>
                <th>Percentage</th>
                <th>Year of Passing</th>
            </tr>
            <tr>
                <td>1</td>
                <td>Class X</td>
                <td><input type="text" name="board1"></td>
                <td><input type="text" name="percentage1"></td>
                <td><input type="text" name="year1"></td>
            </tr>
            <tr>
                <td>2</td>
                <td>Class XII</td>
                <td><input type="text" name="board2"></td>
                <td><input type="text" name="percentage2"></td>
                <td><input type="text" name="year2"></td>
            </tr>
        </table>

        <label>Course applies for:</label>
        <div class="radio-group">
            <input type="radio" name="course" value="BCA"> BCA
            <input type="radio" name="course" value="B.Com"> B.Com
            <input type="radio" name="course" value="B.Sc"> B.Sc
            <input type="radio" name="course" value="B.A"> B.A
        </div>

        <div class="btn-group">
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </div>
    </form>
</div>
</body>
</html>
