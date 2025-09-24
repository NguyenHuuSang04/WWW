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
 * @Class: TinTucFormServlet
 * @Tạo vào ngày: 9/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/tin-tuc-form")
public class TinTucFormServlet extends HttpServlet {
    @Resource(name = "jdbc/quanlydanhmuc")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy tinTucDAO;

    @Override
    public void init() throws ServletException {
        tinTucDAO = new DanhSachTinTucQuanLy(dataSource);
    }

    // Hiển thị form thêm mới tin tức
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DanhMuc> danhMucs = tinTucDAO.getAllDanhMuc();
        req.setAttribute("danhMucs", danhMucs);
        req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
    }

    // Xử lý thêm mới tin tức
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String maTT = req.getParameter("maTT");
        String tieuDe = req.getParameter("tieuDe");
        String noiDungTT = req.getParameter("noiDungTT");
        String lienKet = req.getParameter("lienKet");
        String maDM = req.getParameter("maDM");

        // Validate dữ liệu nhập
        String errorMsg = null;
        if (maTT == null || maTT.isEmpty() ||
                tieuDe == null || tieuDe.isEmpty() ||
                noiDungTT == null || noiDungTT.isEmpty() ||
                lienKet == null || lienKet.isEmpty() ||
                maDM == null || maDM.isEmpty()) {
            errorMsg = "Vui lòng nhập đầy đủ thông tin!";
        } else if (!lienKet.matches("^http://.*")) {
            errorMsg = "Liên kết phải bắt đầu bằng http://";
        } else if (noiDungTT.length() > 255) {
            errorMsg = "Nội dung không được vượt quá 255 ký tự!";
        }

        if (errorMsg != null) {
            List<DanhMuc> danhMucs = tinTucDAO.getAllDanhMuc();
            req.setAttribute("errorMsg", errorMsg);
            req.setAttribute("danhMucs", danhMucs);
            req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
            return;
        }

        TinTuc tinTuc = new TinTuc(maTT, tieuDe, noiDungTT, lienKet, maDM);
        boolean ok = tinTucDAO.addTinTuc(tinTuc);

        if (ok) {
            // Chuyển về trang danh sách tin tức sau khi thêm thành công
            resp.sendRedirect("tintuc");
        } else {
            List<DanhMuc> danhMucs = tinTucDAO.getAllDanhMuc();
            req.setAttribute("errorMsg", "Thêm tin thất bại (có thể mã đã tồn tại)?");
            req.setAttribute("danhMucs", danhMucs);
            req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
        }
    }
}