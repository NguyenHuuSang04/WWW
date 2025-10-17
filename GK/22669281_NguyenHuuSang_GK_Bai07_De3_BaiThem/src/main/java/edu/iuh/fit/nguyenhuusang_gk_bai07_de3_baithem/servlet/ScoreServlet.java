package edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.dao.ScoreDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.dao.StudentDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model.Score;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model.Student;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De3_BaiThem
 * @Class: ScoreServlet
 * @Tạo vào ngày: 9/27/2025
 * @Tác giả: Nguyen Huu Sang
 */

@WebServlet("/scores")
public class ScoreServlet extends HttpServlet {
    private ScoreDAO scoreDAO;
    private StudentDAO studentDAO;

    @Resource(name = "jdbc/schooldb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        scoreDAO = new ScoreDAO(dataSource);
        studentDAO = new StudentDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null || action.isEmpty()) {
            action = "list";
        }
        switch (action) {
            case "list":
                List<Score> scores = scoreDAO.getScoreByIdStudent(req.getParameter("studentId"));

                // Tính GPA
                double gpa = 0;
                if(scores!= null && !scores.isEmpty()) {
                    double total = 0;
                    int count = 0;
                    for(Score s: scores) {
                        total += s.getScore();
                        count++;
                    }
                    gpa = total/count;
                }

                req.setAttribute("gpa", gpa);;
                req.setAttribute("scores", scores);
                req.getRequestDispatcher("score-detai.jsp").forward(req, resp);
                break;
            case "new":
                String id = req.getParameter("studentId");
                Student student = studentDAO.getStudentByID(id);

                req.setAttribute("student", student);
                req.getRequestDispatcher("score-form.jsp").forward(req, resp);
                break;
            case "edit":
                String scoreId = req.getParameter("scoreId");
                String studentId = req.getParameter("studentId");
                Score score = scoreDAO.getScoreByIdScore(scoreId);

                Student student1 = studentDAO.getStudentByID(studentId);

                req.setAttribute("student", student1);
                req.setAttribute("score", score);
                req.getRequestDispatcher("score-form.jsp").forward(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStu = req.getParameter("studentId");
        String idCore = req.getParameter("idCore");
        String subjectName = req.getParameter("subject_name");
        Double scorePoint = Double.valueOf(req.getParameter("score"));
        String semester = req.getParameter("semester");
        LocalDate examDate = LocalDate.parse(req.getParameter("exam_date"));
        String scoreType = req.getParameter("score_type");
        String notes = req.getParameter("notes");

        Student student = studentDAO.getStudentByID(idStu);

        Score score = new Score(idCore, student, subjectName, scorePoint, semester, examDate, scoreType, notes);

        if (idCore != null && !idCore.isEmpty()) {
            scoreDAO.updateScore(score, idStu);
        } else {
            System.out.println(idStu);
            scoreDAO.saveScore(score, idStu);
        }
        resp.sendRedirect(req.getContextPath() + "/scores?action=list&studentId=" + idStu);
    }
}