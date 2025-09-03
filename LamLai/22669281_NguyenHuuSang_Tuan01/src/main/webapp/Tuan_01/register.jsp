<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 8/24/2025
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tuần 1 Bài 04</title>
</head>
<body>
<div class="container">
    <h2>Register</h2>
    <form action="${pageContext.request.contextPath}/processFormUpload" method="post" enctype="multipart/form-data">
        <%--        Name fields--%>
        <div class="form-group">
            <label>Name <span class="required">*</span></label>
            <div class="name-inputs">
                <input type="text" name="firstName" required placeholder="First"/>
                <input type="text" name="lastName" required placeholder="Last"/>
            </div>
        </div>

        <%--            Username field--%>
        <div class="form-group">
            <label>Username <span class="required">*</span></label>
            <input type="text" name="username" required/>
        </div>

        <%--            Email filed--%>
        <div class="form-group">
            <label>E-mail <span class="required">*</span></label>
            <input type="text" name="email" required/>
        </div>

        <%--            Password field--%>
        <div class="form-group">
            <label>Password <span class="required">*</span></label>
            <input type="password" name="password" required/>
        </div>

        <%--            Facebook filed--%>
        <div class="form-group">
            <label>Facebook</label>
            <input type="url" name="facebook" placeholder="Facebook Url"/>
        </div>

        <%--            Gender Selection--%>
        <div class="form-group">
            <label for="">Gender</label>
            <select name="gender" id="">
                <option value="">Select Gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>
        </div>

        <%--            Hobbies checkbok--%>
        <div class="form-group">
            <label for="">Hobbies</label>
            <div class="checkbox-group">
                <label for=""><input type="checkbox" name="hobbies" value="Reading">Reading</label>
                <label for=""><input type="checkbox" name="hobbies" value="sports">sports</label>
                <label for=""><input type="checkbox" name="hobbies" value="music">Music</label>
                <label for=""><input type="checkbox" name="hobbies" value="travel">Travel</label>
            </div>
        </div>

        <%--        Picture--%>
        <div class="form-group">
            <label for="">Profile picture</label>
            <input type="file" name="profilePicture" accept="image/*">
        </div>

        <%--            Bio--%>
        <div class="form-group">
            <label for="">Short Bio</label>
            <textarea name="bio" cols="30" rows="10" placeholder="Share a little bio"></textarea>
        </div>

<%--        Submit button--%>
            <button type="submit">Submit</button>

    </form>


</div>
</body>
</html>
