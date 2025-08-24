package edu.iuh.fit.nguyenhuusang_tuan01;

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
 * @Tạo vào ngày: 8/18/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/login")

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Giả sử tài khoản mặc định
        if ("admin".equals(username) && "123".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);

            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        } else {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}