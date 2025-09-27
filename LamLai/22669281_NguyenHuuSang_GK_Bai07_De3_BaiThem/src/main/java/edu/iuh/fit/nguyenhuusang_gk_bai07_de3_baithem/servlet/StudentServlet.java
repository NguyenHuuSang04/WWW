package edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.dao.StudentDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import javax.sql.DataSource;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De3_BaiThem
 * @Class: StudentServlet
 * @Tạo vào ngày: 9/27/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @
    private DataSource dataSource;
}