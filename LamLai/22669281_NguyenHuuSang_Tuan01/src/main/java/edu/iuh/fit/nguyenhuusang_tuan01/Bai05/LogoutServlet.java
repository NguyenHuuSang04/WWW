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
 * @Class: LogoutServlet
 * @Tạo vào ngày: 8/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // lấy sess hiện tại user nếu có, không có thì null ( không tạo mới )
        if (session != null) {
            session.invalidate();// nếu sess tồn tại thì xóa toàn bộ atribute, sess hết hiệu lực --> user bị xóa khỏi sess khi logout
        }

        resp.sendRedirect(req.getContextPath() + "/Tuan_01/Bai05/login.jsp");// chuyển về login.jsp
    }
}