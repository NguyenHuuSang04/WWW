package edu.iuh.fit.nguyenhuusang_tuan5_bai06.servlet;

import edu.iuh.fit.nguyenhuusang_tuan5_bai06.dao.DanhSachTinTucQuanLy;
import edu.iuh.fit.nguyenhuusang_tuan5_bai06.model.DanhMuc;
import edu.iuh.fit.nguyenhuusang_tuan5_bai06.model.TinTuc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan5_Bai06
 * @Class: QuanLyFormServlet
 * @Tạo vào ngày: 9/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/quan-ly-tin-tuc")
public class QuanLyFormServlet extends HttpServlet {
    @Resource(name = "jdbc/quanlydanhmuc")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy tinTucDAO;

    @Override
    public void init() throws ServletException {
        tinTucDAO = new DanhSachTinTucQuanLy(dataSource);
    }

    // Hiển thị danh sách tin tức để quản lý (xoá)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DanhMuc> danhMucs = tinTucDAO.getAllDanhMuc();
        List<TinTuc> tinTucs = tinTucDAO.getAllTinTuc();
        req.setAttribute("danhMucs", danhMucs);
        req.setAttribute("tinTucs", tinTucs);
        req.getRequestDispatcher("QuanLyForm.jsp").forward(req, resp);
    }

    // Xử lý xoá tin tức
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maTT = req.getParameter("deleteMaTT");
        if (maTT != null && !maTT.isEmpty()) {
            boolean ok = tinTucDAO.deleteTinTuc(maTT);
            if (ok) {
                req.setAttribute("msg", "Đã xoá thành công!");
            } else {
                req.setAttribute("errorMsg", "Xoá thất bại!");
            }
        }
        // Reload lại danh sách sau khi xoá
        List<DanhMuc> danhMucs = tinTucDAO.getAllDanhMuc();
        List<TinTuc> tinTucs = tinTucDAO.getAllTinTuc();
        req.setAttribute("danhMucs", danhMucs);
        req.setAttribute("tinTucs", tinTucs);
        req.getRequestDispatcher("QuanLyForm.jsp").forward(req, resp);
    }
}