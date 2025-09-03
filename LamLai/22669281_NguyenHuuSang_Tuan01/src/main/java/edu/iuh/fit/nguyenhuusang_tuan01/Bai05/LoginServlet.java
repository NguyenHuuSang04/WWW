package edu.iuh.fit.nguyenhuusang_tuan01.Bai05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan01
 * @Class: LoginServlet
 * @Tạo vào ngày: 8/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/login")// khi client gửi request đến /login, sevelet này sẽ xử lý
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // lấy dữ liệu từ form
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        // giả sử tài khoản mặc định
        if ("admin".equals(userName) && "123".equals(password)) {
            HttpSession session = req.getSession();// lấy session của user (chưa có thì tạo mới )
            session.setAttribute("user", userName);// lưu thông tin username vào session --> các request sau có thể thể ai đang đăg nhập ( vd: authFilter sẽ check "user" trong session )

            resp.sendRedirect((req.getContextPath() + "/Tuan_01/Bai05/home.jsp")); // nếu login thành công thì redirect ( chuyển ) trang homne.jsp
        } else {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/Tuan_01/Bai05/login.jsp").forward(req, resp); // nếu login thất bại: báo lỗi, dùng forward để về login.jsp
            // Redirect: chuyển hắn đến trang khác, URL thay đổi
            // forward: không thay đổi URL, chỉ render lại trang JSP
        }
    }
}