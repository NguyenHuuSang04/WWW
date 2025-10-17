package edu.iuh.fit.nguyenhuusang_gk_bai03.servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai03.beans.Product;
import edu.iuh.fit.nguyenhuusang_gk_bai03.dao.ProductDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai03
 * @Class: ProductServlet
 * @Tạo vào ngày: 9/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet({"/products", "/product"})
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init()  {
        productDAO = new ProductDAO(dataSource);
    }


    // request req: đại diện cho yêu cầu ( request ) từ client ( trình duyệt )
    // - chứa: thông tin request: URL, HTTP get post
    // - dữ liệu người dùng gửi lên: tham số form, file upload
    // - Scole lưu trữ tạm: gắn data vào request để gửi qua jsp ( req.setAttribute("product", product)
    // --> req: chính là gói tin người dùng gửi lên servlet

    // respone resp: đại diện cho phản hồi mà servlet trả về cho client
    // resp: là gói tin servlet gửi trả lại client
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");//Lấy tham soo "id" từ URL request

        if(idstr!=null) { // nếu có id thì
            int id = Integer.parseInt(idstr); // chuyển về int để tìm sản phẩm trong DB
            Product product = productDAO.getProductById(id);// gọi DAO tìm
            if(product!=null) {// nếu tìm thấy
                req.setAttribute("product", product);// đưa đối tượng vào resquest để JSP hiển thị
//                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product-detail.jsp");// lấy dispacher trỏ đến trang (/..)
//                dispatcher.forward(req, resp);// hướng hướng nội bộ (forward ) đến trang (/...) ( product-detail)
                req.getRequestDispatcher("/product-detail.jsp").forward(req, resp);
            } else {// nếu không tìm thấy
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }
        }
        List<Product> products = productDAO.getAllProducts();// nếu không có id --> tức là người dùng chỉ vào /products
        req.setAttribute("products", products);
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product-list.jsp");
//        dispatcher.forward(req, resp);
        req.getRequestDispatcher("/product-list.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}