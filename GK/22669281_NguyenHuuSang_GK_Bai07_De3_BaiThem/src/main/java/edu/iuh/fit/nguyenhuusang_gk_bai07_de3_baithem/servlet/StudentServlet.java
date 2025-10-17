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
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De3_BaiThem
 * @Class: StudentServlet
 * @Tạo vào ngày: 9/27/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private ScoreDAO scoreDAO;

    @Resource(name = "jdbc/schooldb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO(dataSource);
        scoreDAO = new ScoreDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null || action.isEmpty()) {
            action = "list";
        }

        switch (action) {
            case "list":
                List<Student> studentList = studentDAO.getAllStudent();
                req.setAttribute("students", studentList);
                req.getRequestDispatcher("student-list.jsp").forward(req, resp);
                break;
            case "viewbyname":
                String nameStu = req.getParameter("studentName");
                List<Student> students = studentDAO.getStudentByName(nameStu);
                req.setAttribute("students", students);
                req.getRequestDispatcher("student-list.jsp").forward(req, resp);
                break;
            case "viewbymajor":
                String majorName = req.getParameter("majorName");
                List<Student> students1 = studentDAO.getStudentByMajor(majorName);
                req.setAttribute("students", students1);
                req.getRequestDispatcher("student-list.jsp").forward(req, resp);
                break;
            case "viewbyid":// hiển thị thông tin 1 sv, và cả bảng điểm ( jsp có thể lấy score từ đây )
                String idStu = req.getParameter("studentId");
                Student student = studentDAO.getStudentByID(idStu);
                List<Score> scoreList = scoreDAO.getScoreByIdStudent(student.getStudentID());
                req.setAttribute("scores", scoreList);
                req.setAttribute("student", student);
                req.getRequestDispatcher("student-detail.jsp").forward(req,resp);
                break;
            case "viewScoreByStudentId":
                String idStudent = req.getParameter("studentId");
                Student stu = studentDAO.getStudentByID(idStudent);
                List<Score> scores = scoreDAO.getScoreByIdStudent(stu.getStudentID());
                req.setAttribute("student", stu);
                req.setAttribute("scores", scores);
                req.getRequestDispatcher("student-detail.jsp").forward(req, resp);
                break;
                // cập nhật thông tin sinh viên
            case "edit":
                String idStu_1 = req.getParameter("studentId");
                Student stu_1 = studentDAO.getStudentByID(idStu_1);
                List<Score> scores_1 = scoreDAO.getScoreByIdStudent(stu_1.getStudentID());
                req.setAttribute("student", stu_1);
                req.setAttribute("scores", scores_1);
                req.getRequestDispatcher("student-detail.jsp").forward(req, resp);
                break;
                // xóa sinh viên
            case "remove":
                studentDAO.removeStudent(req.getParameter("studentId"));
                resp.sendRedirect("student-list.jsp");
                break;
            case "new":
                req.getRequestDispatcher("student-form.jsp").forward(req, resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String className = req.getParameter("class_name");
        String major = req.getParameter("major");
        int enrollmentYear = Integer.parseInt(req.getParameter("enrollment_year"));
        String status = req.getParameter("status");
        LocalDate createDate = LocalDate.parse(req.getParameter("created_date"));
        Student student = new Student(studentId,fullName, email, phone, className, major, enrollmentYear, status, createDate);

        if (studentId == null || studentId.isEmpty()) {
            studentDAO.saveStudent(student);
        } else {
            studentDAO.updateStudent(student);
        }
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}