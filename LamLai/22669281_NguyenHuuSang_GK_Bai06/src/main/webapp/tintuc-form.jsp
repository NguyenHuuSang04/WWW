<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/26/2025
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<script>
    function validateTinTinForm() {

        var tieuDe = document.forms["tintucForm"]["tieuDe"].value.trim();
        var noiDung = document.forms["tintucForm"]["noiDung"].value.trim();
        var lienKet = document.forms["tintucForm"]["lienKet"].value.trim();
        var maDM = document.forms["tintucForm"]["maDM"].value.trim();

        var errors = [];

        // kiểm tra bắt buộc nhập
        if(!tieuDe) errors.push("Tiêu đề là bắt buộc");
        if(!noiDung) errors.push("Nội dung là bắt buộc");
        if(!lienKet) errors.push("Liên kết là bắt buộc");
        if(!maDM) errors.push("danh mục là bắt buộc");

        if(lienKet && !/^http:\/\//.test(lienKet)) {
            errors.push("Liên kết phỉa bắt đầu bằng http://");
        }

        // Nội dung không quà 255 ký tự
        if(noiDung.length > 255){
            errors.push("Nội dung không được vượt quá 255 ký tự");
        }

        if(errors.length > 0) {
            alert(errors.join("\n"));
            return false;
        }
        return true;

    }

</script>

<body>
    <form name = "tintucForm" action="${pageContext.request.contextPath}/tintucform" method="post" onsubmit="return validateTinTinForm();">
        <input type="hidden" name="maTT" value="${tinTuc.maTT}"> <br>
        Tiêu đề: <input type="text" name="tieuDe" value="${tinTuc.tieuDe}"> <br>
        Nội dung: <input type="text" name="noiDung" value="${tinTuc.noiDungTT}"><br>
        liên kết: <input type="text" name="lienKet" value="${tinTuc.lienKet}"><br>
        Danh mục:
            <select name="maDM">
                <c:forEach items="${danhMucs}" var="danhMuc">
                    <option value="${danhMuc.maDM}">
                        ${danhMuc.tenDM}
                    </option
                </c:forEach>
            </select>
        <input type="submit" value="save">
    </form>
</body>
</html>
