package edu.iuh.fit.nguyenhuusang_tuan04_bai04.servlet;

import edu.iuh.fit.nguyenhuusang_tuan04_bai04.beans.CartBean;
import edu.iuh.fit.nguyenhuusang_tuan04_bai04.beans.Book;
import edu.iuh.fit.nguyenhuusang_tuan04_bai04.dao.BookDAO;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04_Bai04
 * @Class: CartServlet
 * @Tạo vào ngày: 9/15/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private BookDAO bookDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("giohang.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                String id = req.getParameter("id");
                int quantity = 1;
                try {
                    String qtyStr = req.getParameter("quantity");
                    if (qtyStr != null) {
                        quantity = Integer.parseInt(qtyStr);
                    }
                } catch (Exception ex) {
                    quantity = 1;
                }
                Book book = bookDAO.getBookById(id);
                cart.addBook(book, quantity);
            } else if ("update".equals(action)) {
                String bookId = req.getParameter("bookId");
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                cart.updateBookQuantity(bookId, quantity);
            } else if ("remove".equals(action)) {
                String bookId = req.getParameter("bookId");
                cart.removeBook(bookId);
            } else if ("clear".equals(action)) {
                cart.clear();
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        resp.sendRedirect("cart");
    }
}