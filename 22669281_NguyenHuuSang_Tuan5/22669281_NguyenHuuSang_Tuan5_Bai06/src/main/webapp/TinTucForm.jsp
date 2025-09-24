<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp"/>
<h2 style="color:#2156a4;">Thêm mới tin tức</h2>

<!-- Hiển thị lỗi nhập liệu (nếu có) -->
<c:if test="${not empty errorMsg}">
    <p style="color:#c00; font-weight: bold;">${errorMsg}</p>
</c:if>

<form action="tintuc?action=add" method="post" id="addNewsForm" autocomplete="off">
    <label for="maTT">Mã tin tức:</label><br>
    <input type="text" id="maTT" name="maTT" required value="${param.maTT != null ? param.maTT : ''}"><br><br>

    <label for="tieuDe">Tiêu đề:</label><br>
    <input type="text" id="tieuDe" name="tieuDe" required value="${param.tieuDe != null ? param.tieuDe : ''}"><br><br>

    <label for="lienKet">Liên kết:</label><br>
    <input type="url" id="lienKet" name="lienKet" required pattern="http://.*" value="${param.lienKet != null ? param.lienKet : ''}"
           placeholder="http://example.com"><br>
    <small style="color:#555;">Liên kết phải bắt đầu bởi "http://"</small><br><br>

    <label for="noiDungTT">Nội dung:</label><br>
    <textarea id="noiDungTT" name="noiDungTT" rows="4" maxlength="255" required
              pattern=".{1,255}">${param.noiDungTT != null ? param.noiDungTT : ''}</textarea>
    <br>
    <small style="color:#555;">Tối đa 255 ký tự</small><br><br>

    <label for="maDM">Danh mục:</label><br>
    <select id="maDM" name="maDM" class="select-category" required>
        <option value="">--Chọn danh mục--</option>
        <c:forEach var="dm" items="${danhMucs}">
            <option value="${dm.maDM}" <c:if test="${param.maDM eq dm.maDM}">selected</c:if>>
                    ${dm.tenDanhMuc}
            </option>
        </c:forEach>
    </select><br><br>

    <button type="submit" class="btn-view">Thêm tin mới</button>
    <button type="reset">Nhập lại</button>
</form>

<script>
    document.getElementById('addNewsForm').onsubmit = function() {
        // Kiểm tra liên kết phải bắt đầu bằng http://
        var link = document.getElementById('lienKet').value;
        if (!/^http:\/\/.+/.test(link)) {
            alert("Liên kết phải bắt đầu bằng http://");
            document.getElementById('lienKet').focus();
            return false;
        }
        // Kiểm tra nội dung không quá 255 ký tự
        var nd = document.getElementById('noiDungTT').value;
        if (nd.length > 255) {
            alert("Nội dung tin không được vượt quá 255 ký tự!");
            document.getElementById('noiDungTT').focus();
            return false;
        }
        return true;
    }
</script>
<jsp:include page="includes/footer.jsp"/>