package edu.iuh.fit.nguyenhuusang_tuan01;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan01
 * @Class: AuthFilter
 * @Tạo vào ngày: 8/18/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebFilter("/secure/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn) {
            chain.doFilter(request, response); // Cho phép đi tiếp
        } else {
            res.sendRedirect(req.getContextPath() + "/login.jsp"); // Chưa login → về login
        }
    }
}