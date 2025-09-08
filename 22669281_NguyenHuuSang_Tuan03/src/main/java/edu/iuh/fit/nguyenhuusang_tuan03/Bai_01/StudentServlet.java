package edu.iuh.fit.nguyenhuusang_tuan03.Bai_01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan03
 * @Class: StudentServlet
 * @Tạo vào ngày: 9/8/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/registerStudent")
@MultipartConfig
public class StudentServlet extends HttpServlet {
    public StudentServlet () {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dobStr = req.getParameter("dob");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String genderStr = req.getParameter("gender");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        String[] hobbies = req.getParameterValues("hobbies");
        String course = req.getParameter("course");

        // Qualification
        String board1 = req.getParameter("board1");
        String percentage1Str = req.getParameter("percentage1");
        String year1Str = req.getParameter("year1");

        String board2 = req.getParameter("board2");
        String percentage2Str = req.getParameter("percentage2");
        String year2Str = req.getParameter("year2");

        // Xử lý dữ liệu
        LocalDate dob = null;
        try {
            dob = LocalDate.parse(dobStr);
        } catch (Exception e) {
            // ignore for demo
        }

        boolean gender = "Male".equalsIgnoreCase(genderStr);

        double percentage1 = 0;
        int year1 = 0;
        double percentage2 = 0;
        int year2 = 0;
        try {
            percentage1 = Double.parseDouble(percentage1Str);
        } catch (Exception e) {}
        try {
            year1 = Integer.parseInt(year1Str);
        } catch (Exception e) {}
        try {
            percentage2 = Double.parseDouble(percentage2Str);
        } catch (Exception e) {}
        try {
            year2 = Integer.parseInt(year2Str);
        } catch (Exception e) {}

        List<Qualification> qualifications = new ArrayList<>();
        qualifications.add(new Qualification("Class X", board1, percentage1, year1));
        qualifications.add(new Qualification("Class XII", board2, percentage2, year2));

        // Gán vào đối tượng Student
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDateOfBirth(dob);
        student.setEmail(email);
        student.setMobileNumber(mobile);
        student.setGender(gender);
        student.setAddress(address);
        student.setCity(city);
        student.setState(state);
        student.setCountry(country);
        student.setHobbies(hobbies);
        student.setQualifications(qualifications);
        student.setCourse(course);

        // Đẩy dữ liệu sang trang kết quả
        req.setAttribute("student", student);
        req.getRequestDispatcher("/Bai_01/META-INF/student_result.jsp").forward(req, resp);
    }
}