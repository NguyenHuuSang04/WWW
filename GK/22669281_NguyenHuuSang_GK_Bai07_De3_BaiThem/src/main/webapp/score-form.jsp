<%--
  Created by IntelliJ IDEA.
  User: HPP
  Date: 9/28/2025
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<script>
    function ValiddateScoreForm(){
        var scorePoint = document.forms["scoreForm"]["score"].value.trim();
        var examDate = document.forms["scoreForm"]["exam_date"].value.trim();

        var erross=[];

        // kiểm tra bắt buộc nhập
        if(!scorePoint) erross.push("Điểm không được trống");
        if(!examDate) erross.push("Ngày thi không được trống");

        // kiểm tra điểm
        if(scorePoint) {
            var scoreVal = parseFloat(scorePoint);
            if (isNaN(scoreVal) || scoreVal<0 || scoreVal>10) erross.push("Điểm từ 0 -10");
        }

        // kiểm tra ngày
        if(examDate) {
            var today = new Date();
            today.setHours(0,0,0,0); // loại bỏ giờ phút giây
            var  exam = new Date(examDate);
            if(exam > today) erross.push("Ngày không được trước ngày hiện tại");
        }


        if (erross.length > 0) {
            alert(erross.join("\n"));
            return false;
        }
        return true;
    }
</script>

<body>
    <form name="scoreForm" action="${pageContext.request.contextPath}/scores" method="post" onsubmit="return ValiddateScoreForm();">
        <input type="hidden"  name="idCore" value="${score.scoreId}">
        <input type="hidden" name="studentId" value="${student.studentID}">

        Tên môn <input type="text"  name="subject_name" value="${score.subjectName}"> <br>
        Điểm <input type="number" name="score" value="${score.score}"> <br>
        Học kỳ <input type="text" name="semester" value="${score.semester}"> <br>
        Ngày thi <input type="date" name="exam_date" value="${score.exemDate}"> <br>
        Loại điểm <input type="text" name="score_type" value="${score.scoreType}"> <br>
        Ghi chú <input type="text" name="notes" value="${score.notes}"> <br>
        <input type="submit" value="Nộp">
    </form>
</body>
</html>
