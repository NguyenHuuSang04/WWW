package edu.iuh.fit.nguyenhuusang_tuan04.servlet;

import edu.iuh.fit.nguyenhuusang_tuan04.beans.Product;
import edu.iuh.fit.nguyenhuusang_tuan04.dao.ProductDAO;

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
 * @Dự án: 22669281_NguyenHuuSang_Tuan04
 * @Class: ProductServlet
 * @Tạo vào ngày: 9/15/2025
 * @Tác giả: Nguyen Huu Sang
 * @Mô tả: Controller xử lý lấy thông tin sản phẩm (danh sách và chi tiết)
 */
@WebServlet({"/products", "/product"})
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() {
        productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");

        if (idstr != null) {
            int id = Integer.parseInt(idstr);
            Product product = productDAO.getProductById(id);
            if (product != null) {
                req.setAttribute("product", product);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product-detail.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            }
            return;
        }

        List<Product> products = productDAO.getAllProducts();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product-list.jsp");
        dispatcher.forward(req, resp);
    }
}