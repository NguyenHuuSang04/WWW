package edu.iuh.fit.nguyenhuusang_gk_bai06.servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai06.dao.DanhMucDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai06.dao.DanhSachTinTucQuanLyDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai06.model.DanhMuc;
import edu.iuh.fit.nguyenhuusang_gk_bai06.model.TinTuc;
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
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai06
 * @Class: DanhSachTinTucServlet
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/tintucs")
public class DanhSachTinTucServlet extends HttpServlet {
    private DanhSachTinTucQuanLyDAO danhSachTinTucQuanLyDAO;
    private DanhMucDAO danhMucDAO;

    @Resource(name = "jdbc/quanlydanhmuc")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        danhSachTinTucQuanLyDAO =new DanhSachTinTucQuanLyDAO(dataSource);
        danhMucDAO = new DanhMucDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list": // R
                List<TinTuc> tinTucs = danhSachTinTucQuanLyDAO.getAllTinTuc();
                req.setAttribute("tintucs", tinTucs);
                req.getRequestDispatcher("tintuc-list.jsp").forward(req,resp);;
                break;
//            case "new": // C
//                List<DanhMuc> danhMucs = danhMucDAO.getAllDanhMuc();
//                req.setAttribute("danhMucs", danhMucs);
//                req.getRequestDispatcher("tintuc-form.jsp").forward(req, resp);
//                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}