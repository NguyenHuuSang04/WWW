package edu.iuh.fit.nguyenhuusang_tuan01.Bai05;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan01
 * @Class: AuthFilter
 * @Tạo vào ngày: 8/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebFilter("/secure/*") // mọi reqquest có URL bắt đầu /secure/ sẽ đi qua filter này trước.
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request; // ép kiểu để dùng các method của HttpServletRequest
        HttpServletResponse res = (HttpServletResponse) response;

        // Nếu người dùng chưa login thì session là null
        HttpSession session = req.getSession(false);// chưa có session thì trả về null, không tạo mới

        boolean loggedIn = (session != null && session.getAttribute("user") != null);// kiểm tra đã login chưa
        // đã có session chưa, getAttribute("user") != null session có chứa thông tin user

        if (loggedIn) { // nếu đã login cho request đi tiếp, nếu chưa thì về trang login
            chain.doFilter(request, response);// cho phép đi tiếp
        } else  {
            res.sendRedirect(req.getContextPath() + "/login.jsp");// chưa login → về login
        }
    }
}