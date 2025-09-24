<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Department Information</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background: #f8f9fb;
        }
        .container {
            margin-top: 38px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.09);
            padding-bottom: 30px;
            max-width: 480px;
        }
        .banner-img {
            width: 100%;
            border-radius: 10px 10px 0 0;
            object-fit: cover;
            margin-bottom: 18px;
        }
        h2 {
            color: #5839a5;
            margin-top: 18px;
            font-weight: 700;
            text-align: center;
            margin-bottom: 20px;
        }
        .form-label {
            font-weight: 500;
            color: #3f366b;
        }
        .form-control {
            border-radius: 7px;
            font-size: 16px;
        }
        .btn-save {
            background: #3b82f6;
            color: white;
            border-radius: 7px;
            padding: 8px 34px;
            font-weight: bold;
            margin-top: 10px;
            letter-spacing: 1px;
        }
        .btn-save:hover {
            background: #2563eb;
        }
    </style>
</head>
<body>
<div class="container shadow">
    <img class="banner-img" src="${pageContext.request.contextPath}/images/HRbanner.jpg" alt="HR Banner" height="180"/>
    <h2>Department Information</h2>
    <form action="${pageContext.request.contextPath}/departments" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input id="name" type="text" class="form-control" name="name"
                   value="${department != null ? department.name : ''}" required/>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-save">Save</button>
        </div>
    </form>
</div>
</body>
</html>