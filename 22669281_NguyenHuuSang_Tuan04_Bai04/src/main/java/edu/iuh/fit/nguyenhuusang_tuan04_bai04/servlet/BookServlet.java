package edu.iuh.fit.nguyenhuusang_tuan04_bai04.servlet;

import edu.iuh.fit.nguyenhuusang_tuan04_bai04.beans.Book;
import edu.iuh.fit.nguyenhuusang_tuan04_bai04.dao.BookDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04_Bai04
 * @Class: BookServlet
 * @Tạo vào ngày: 9/15/2025
 * @Tác giả: Nguyen Huu Sang
 */

@WebServlet({"/books", "/book"})
public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() {
        bookDAO = new BookDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            // Hiển thị chi tiết sách
            Book book = bookDAO.getBookById(id);
            if (book != null) {
                req.setAttribute("book", book);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/chitietsach.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
            }
            return;
        }

        // Hiển thị danh sách sách
        List<Book> books = bookDAO.getAllBooks();
        req.setAttribute("books", books);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/danhsach.jsp");
        dispatcher.forward(req, resp);
    }
}