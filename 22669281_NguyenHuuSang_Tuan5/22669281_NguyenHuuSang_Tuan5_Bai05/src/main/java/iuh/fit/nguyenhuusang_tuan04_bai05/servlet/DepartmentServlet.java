package iuh.fit.nguyenhuusang_tuan04_bai05.servlet;

import iuh.fit.nguyenhuusang_tuan04_bai05.dao.DepartmentDAO;
import iuh.fit.nguyenhuusang_tuan04_bai05.dao.EmployeeDAO;
import iuh.fit.nguyenhuusang_tuan04_bai05.model.Department;
import iuh.fit.nguyenhuusang_tuan04_bai05.model.Employee;
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

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
    @Resource(name = "jdbc/tuan04_bai05")
    private DataSource dataSource;

    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        departmentDAO = new DepartmentDAO(dataSource);
        employeeDAO = new EmployeeDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                // Hiển thị form thêm mới
                req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                break;
            case "edit":
                // Hiển thị form sửa, truyền department lên form
                int editId = Integer.parseInt(req.getParameter("id"));
                Department editDep = departmentDAO.getDepartmentById(editId);
                req.setAttribute("department", editDep);
                req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                break;
            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                departmentDAO.delete(deleteId);
                resp.sendRedirect("departments");
                break;
            case "employees":
                // Hiển thị danh sách nhân viên theo department
                int deptId = Integer.parseInt(req.getParameter("id"));
                List<Employee> employees = employeeDAO.getAllByDepartment(deptId);
                req.setAttribute("employees", employees);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
            default:
                // Hiển thị danh sách, có tìm kiếm nếu có q
                String q = req.getParameter("q");
                List<Department> departments;
                if (q != null && !q.trim().isEmpty()) {
                    departments = departmentDAO.searchByName(q.trim());
                } else {
                    departments = departmentDAO.getAll();
                }
                req.setAttribute("departments", departments);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xử lý thêm/sửa phòng ban
        String idParam = req.getParameter("id");
        String name = req.getParameter("name");

        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
        Department dep = new Department(id, name);

        if (id != 0) {
            departmentDAO.update(dep);
        } else {
            departmentDAO.save(dep);
        }

        resp.sendRedirect("departments");
    }
}