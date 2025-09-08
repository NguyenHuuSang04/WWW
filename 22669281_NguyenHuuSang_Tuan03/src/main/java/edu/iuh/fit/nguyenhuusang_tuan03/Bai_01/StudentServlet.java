package edu.iuh.fit.nguyenhuusang_tuan03.Bai_01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
    }
}