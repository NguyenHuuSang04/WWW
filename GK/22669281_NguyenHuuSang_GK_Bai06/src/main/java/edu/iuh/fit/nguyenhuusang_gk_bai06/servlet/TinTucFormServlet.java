package edu.iuh.fit.nguyenhuusang_gk_bai06.servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai06.dao.DanhMucDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai06.dao.DanhSachTinTucQuanLyDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai06.model.DanhMuc;
import edu.iuh.fit.nguyenhuusang_gk_bai06.model.TinTuc;
import jakarta.annotation.Resource;
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
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai06
 * @Class: TinTucFormServlet
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/tintucform")
public class TinTucFormServlet extends HttpServlet {
    private DanhSachTinTucQuanLyDAO danhSachTinTucQuanLyDAO;
    private DanhMucDAO danhMucDAO;

    @Resource(name = "jdbc/quanlydanhmuc")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        danhSachTinTucQuanLyDAO = new DanhSachTinTucQuanLyDAO(dataSource);
        danhMucDAO = new DanhMucDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "new":
                List<DanhMuc> danhMucs = danhMucDAO.getAllDanhMuc();
                req.setAttribute("danhMucs", danhMucs);
                req.getRequestDispatcher("tintuc-form.jsp").forward(req, resp);
                break;
            case "edit":
                String id = req.getParameter("maTT");
                TinTuc tinTuc = danhSachTinTucQuanLyDAO.getTinTucById(id);
                List<DanhMuc> danhMucList = danhMucDAO.getAllDanhMuc();

                req.setAttribute("tinTuc", tinTuc);
                req.setAttribute("danhMucs", danhMucList);
                req.getRequestDispatcher("tintuc-form.jsp").forward(req,resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("maTT");
        String tieuDe = req.getParameter("tieuDe");
        String noiDung = req.getParameter("noiDung");
        String lienKet = req.getParameter("lienKet");
        String maDM = req.getParameter("maDM");

        DanhMuc danhMuc = danhMucDAO.getDanhMucById(maDM);

        TinTuc tinTuc = new TinTuc(id, tieuDe, noiDung, lienKet, danhMuc);
        if (id != null && !id.trim().isEmpty()) {
            danhSachTinTucQuanLyDAO.updateTinTuc(tinTuc);
        } else {
            danhSachTinTucQuanLyDAO.saveTinTuc(tinTuc);
        }
        resp.sendRedirect(req.getContextPath() + "/tintucs");

    }
}