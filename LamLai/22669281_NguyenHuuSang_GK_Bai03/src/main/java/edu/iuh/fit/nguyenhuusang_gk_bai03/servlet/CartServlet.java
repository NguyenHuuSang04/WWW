package edu.iuh.fit.nguyenhuusang_gk_bai03.servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai03.beans.CartBean;
import edu.iuh.fit.nguyenhuusang_gk_bai03.beans.Product;
import edu.iuh.fit.nguyenhuusang_gk_bai03.dao.ProductDAO;
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
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai03
 * @Class: CartServlet
 * @Tạo vào ngày: 9/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Resource(name="jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            productDAO = new ProductDAO(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();// lấy sess hiện tại của user ( chưa có thì tạo mới) dùng để lưu cart
        CartBean cart = (CartBean) session.getAttribute("cart"); // lấy cart từ sess nếu user đã có giỏ từ trc
        if(cart == null) { // nếu ch có, tạo CartBean mới và lưu vào sess để dùng cho các request tiếp theo
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");// lấy tham số action từ form/ request để thao tác add, update
        try {
            if("add".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));// lấy id từ form
                Product p = productDAO.getProductById(id);// tìm sản phẩm đó từ id trong dao
                cart.addProduct(p);// add p đó vào cart
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("productId"));// lấy id từ form
                int quality = Integer.parseInt(req.getParameter("quantity")); // lấy quantity
                cart.updateProduct(id, quality); // update
            } else if ("remove".equals(action)) {
                int id = Integer.parseInt(req.getParameter("productId"));// lấy id
                cart.removeProduct(id);
            } else if ("clear".equals(action)) {
                cart.clear();
            }
        } catch (Exception e) {
            throw  new ServletException(e);
        }
        resp.sendRedirect("cart");// sau khi xử lý giỏ hàng, chuyển hướng trình duyệt về /cart để hiển thị giỏ hàng mới, đồng thời tránh việc POST bị gửi lại khi refresh;
    }
}