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
 * @Class: TinTucServlet
 * @Tạo vào ngày: 9/23/2025
 * @Tác giả: Nguyen Huu Sang
 */

@WebServlet("/tintuc")
public class DanhSachTinTucServlet extends HttpServlet {
    @Resource(name = "jdbc/quanlydanhmuc")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy danhSachTinTucQuanLy;

    @Override
    public void init() throws ServletException {
        danhSachTinTucQuanLy = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                hienThiFormThemMoi(req, resp);
                break;
            case "manage":
                hienThiQuanLyTinTuc(req, resp);
                break;
            default:
                hienThiDanhSachTinTuc(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            themTinTucMoi(req, resp);
        } else if ("delete".equals(action)) {
            xoaTinTuc(req, resp);
        } else {
            resp.sendRedirect("tintuc");
        }
    }

    // Hiển thị danh sách tin tức (có lọc danh mục)
    private void hienThiDanhSachTinTuc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String madm = req.getParameter("madm");
        List<DanhMuc> danhMucs = danhSachTinTucQuanLy.getAllDanhMuc();
        List<TinTuc> tinTucs = (madm != null && !madm.isEmpty())
                ? danhSachTinTucQuanLy.getTinTucByMaDM(madm)
                : danhSachTinTucQuanLy.getAllTinTuc();
        req.setAttribute("danhMucs", danhMucs);
        req.setAttribute("tinTucs", tinTucs);
        req.getRequestDispatcher("DanhSachTinTuc.jsp").forward(req, resp);
    }

    // Hiển thị form thêm mới tin tức
    private void hienThiFormThemMoi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DanhMuc> danhMucs = danhSachTinTucQuanLy.getAllDanhMuc();
        req.setAttribute("danhMucs", danhMucs);
        req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
    }

    // Thêm tin tức mới (validate)
    private void themTinTucMoi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String maTT = req.getParameter("maTT");
        String tieuDe = req.getParameter("tieuDe");
        String noiDungTT = req.getParameter("noiDungTT");
        String lienKet = req.getParameter("lienKet");
        String maDM = req.getParameter("maDM");

        String errorMsg = "";
        if (maTT == null || maTT.isEmpty() ||
                tieuDe == null || tieuDe.isEmpty() ||
                noiDungTT == null || noiDungTT.isEmpty() ||
                lienKet == null || lienKet.isEmpty() ||
                maDM == null || maDM.isEmpty()) {
            errorMsg = "Vui lòng nhập đầy đủ thông tin!";
        } else if (!lienKet.startsWith("http://")) {
            errorMsg = "Liên kết phải bắt đầu bằng http://";
        } else if (noiDungTT.length() > 255) {
            errorMsg = "Nội dung không được vượt quá 255 ký tự!";
        }

        if (!errorMsg.isEmpty()) {
            List<DanhMuc> danhMucs = danhSachTinTucQuanLy.getAllDanhMuc();
            req.setAttribute("errorMsg", errorMsg);
            req.setAttribute("danhMucs", danhMucs);
            req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
            return;
        }

        TinTuc tinTuc = new TinTuc(maTT, tieuDe, noiDungTT, lienKet, maDM);
        boolean ok = danhSachTinTucQuanLy.addTinTuc(tinTuc);

        if (ok) {
            resp.sendRedirect("tintuc");
        } else {
            List<DanhMuc> danhMucs = danhSachTinTucQuanLy.getAllDanhMuc();
            req.setAttribute("errorMsg", "Thêm tin thất bại (có thể mã đã tồn tại)?");
            req.setAttribute("danhMucs", danhMucs);
            req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
        }
    }

    // Hiển thị trang quản lý (xoá)
    private void hienThiQuanLyTinTuc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DanhMuc> danhMucs = danhSachTinTucQuanLy.getAllDanhMuc();
        List<TinTuc> tinTucs = danhSachTinTucQuanLy.getAllTinTuc();
        req.setAttribute("danhMucs", danhMucs);
        req.setAttribute("tinTucs", tinTucs);
        req.getRequestDispatcher("QuanLyForm.jsp").forward(req, resp);
    }

    // Xoá tin tức
    private void xoaTinTuc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maTT = req.getParameter("deleteMaTT");
        if (maTT != null && !maTT.isEmpty()) {
            boolean ok = danhSachTinTucQuanLy.deleteTinTuc(maTT);
            if (ok) {
                req.setAttribute("msg", "Đã xoá thành công!");
            } else {
                req.setAttribute("errorMsg", "Xoá thất bại!");
            }
        }
        hienThiQuanLyTinTuc(req, resp);
    }
}