package edu.iuh.fit.nguyenhuusang_gk_bai05.servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai05.dao.DepartmentDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai05.dao.EmployeeDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai05.model.Department;
import edu.iuh.fit.nguyenhuusang_gk_bai05.model.Employee;
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
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai05
 * @Class: DepartmentServlet
 * @Tạo vào ngày: 9/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    @Resource(name = "jdbc/employeedb")
    private DataSource dataSource;


    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            departmentDAO = new DepartmentDAO(dataSource);
            employeeDAO = new EmployeeDAO(dataSource);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null) action= "list";

        switch (action) {
            case "list":// getAll
                List<Department> departments = departmentDAO.getAllDepartment();
                req.setAttribute("departments", departments);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                break;
            case "new":// C
                req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                break;
            case "edit": // U
                String deptId = req.getParameter("id");
                Department department = departmentDAO.getDepartmentByID(deptId);

                req.setAttribute("department", department);
                req.getRequestDispatcher("department-form.jsp").forward(req,resp);
                break;
            case "delete": // D
                departmentDAO.removeDepartment(req.getParameter("id"));
                resp.sendRedirect("departments");
                break;
            case "search":
                String keyword = req.getParameter("keyword");
                List<Department> departmentsList;
                if(keyword != null && !keyword.trim().isEmpty()) {
                    departmentsList = departmentDAO.searchDepartmentByName(keyword);
                } else {
                    departmentsList = departmentDAO.getAllDepartment();
                }
                req.setAttribute("departments", departmentsList);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                break;

            case "addEmployee":
                // chưa biết viết
                String departId = req.getParameter("deptId");
                Department department1 = departmentDAO.getDepartmentByID(departId);
                req.setAttribute("department", department1);
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);


        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptId = req.getParameter("id");
        String name = req.getParameter("name");
        Department department = new Department(deptId, name);

        if(deptId!=null && !deptId.isEmpty()) {
            departmentDAO.updateDepartment(department);
        } else {
            departmentDAO.saveDepartment(department);
        }
        resp.sendRedirect(req.getContextPath() + "/departments");
    }
}