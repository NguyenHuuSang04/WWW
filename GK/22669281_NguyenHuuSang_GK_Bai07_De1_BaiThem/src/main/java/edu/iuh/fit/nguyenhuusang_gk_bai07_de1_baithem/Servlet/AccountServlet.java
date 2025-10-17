package edu.iuh.fit.nguyenhuusang_gk_bai07_de1_baithem.Servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai07_de1_baithem.dao.AccountDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de1_baithem.model.Account;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De1_BaiThem
 * @Class: AccountServlet
 * @Tạo vào ngày: 9/29/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/accounts")
public class AccountServlet extends HttpServlet {
    private AccountDAO accountDAO;

    @Resource(name = "jdbc/nocostbank")
    private DataSource dataSource;


    @Override
    public void init() throws ServletException {
        try {
            accountDAO = new AccountDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";
        }

        switch (action) {
            case "list":
                List<Account> accounts = accountDAO.getAll();
                req.setAttribute("accounts", accounts);
                req.getRequestDispatcher("account-list.jsp").forward(req, resp);
                break;
            case "search":
                String criteria = req.getParameter("criteria");
                String keyword = req.getParameter("keyword");
                List<Account> accountsResult;
                if (keyword == null || keyword.trim().isEmpty()) {
                    accountsResult = accountDAO.getAll();
                } else if ("name".equals(criteria)) {
                    accountsResult = accountDAO.filterAccountByName(keyword);
                } else if ("address".equals(criteria)) {
                    accountsResult = accountDAO.filterAccountByAdd(keyword);
                } else {
                    accountsResult = accountDAO.getAll();
                }
                req.setAttribute("accounts", accountsResult);
                req.getRequestDispatcher("account-list.jsp").forward(req, resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}