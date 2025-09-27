package edu.iuh.fit.nguyenhuusang_tuan04_bai04.servlet;

import edu.iuh.fit.nguyenhuusang_tuan04_bai04.dao.BookDAO;
import edu.iuh.fit.nguyenhuusang_tuan04_bai04.model.Book;
import edu.iuh.fit.nguyenhuusang_tuan04_bai04.model.CartBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04_Bai04
 * @Class: CartServlet
 * @Tạo vào ngày: 9/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Resource(name = "jdbc/bookdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try{
            bookDAO = new BookDAO(dataSource);
        }catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cartBean = (CartBean) session.getAttribute("cart");
        if(cartBean == null) {
            cartBean = new CartBean();
            session.setAttribute("cart", cartBean);
        }

        String action = req.getParameter("action");

        try {
            if("add".equals(action)) {
                String id = req.getParameter("id");
                Book b = bookDAO.getBookById(id);
                cartBean.addBook(b);;
            } else if ("update".equals(action)) {
                String id = req.getParameter("id");
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                cartBean.updateBook(id, quantity);
            } else if ("remove".equals(action)) {
                String id = req.getParameter("id");
                cartBean.removeBook(id);
            } else if ("clear".equals(action)) {
                cartBean.clear();
            }
        } catch (Exception e) {
            throw  new ServletException(e);
        }
        resp.sendRedirect("cart");;
    }
}