<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/28/2025
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add student</title>
</head>
<script>
        function ValidFormStudent() {
            var ten = document.forms["FormStudent"]["full_name"].trim();
            var email = document.forms["FormStudent"]["email"].trim();
            var sdt = document.forms["FormStudent"]["phone"].trim();
            var lop = document.forms["FormStudent"]["class_name"].trim();


            var status = document.forms["FormStudent"]["status"].trim();
            var date = document.forms["FormStudent"]["created_date"].trim();

            var errors=[];

            if(ten == null || ten.isEmpty()) {
                errors.push("Tên không được rỗng")
            }

        }
</script>
<body>
    <form name="FormStudent" action="${pageContext.request.contextPath}/students" method="post">
        <input type="hidden" name="studentId" value="${student.studentId}">
            Tên: <input type="text" name="full_name" required> <br>
            Email: <input type="text" name="email" required> <br>
            Sdt: <input type="text" name="phone" required> <br>
            Lớp: <input type="text" name="class_name" required> <br>
            Ngành:
                <select name="major" required>
                    <option value="Information Techonology">Information techonology</option>
                    <option value="Software Engineering">Software Engineering</option>
                    <option value="Computer Science">Computer Science</option>
                    <option value="Data Science">Data Science</option>
                    <option value="Artificial Intelligence">Artificial Intelligence</option>
                </select><br>

            Năm nhập học:
                <select name="enrollment_year" required>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                    <option value="2025">2025</option>
                </select>
            Status: <input type="text" name="status" required> <br>
            Ngày taoj: <input type="date" name="created_date" required> <br>

        <input type="submit" value="Submit">

    </form>
</body>
</html>

