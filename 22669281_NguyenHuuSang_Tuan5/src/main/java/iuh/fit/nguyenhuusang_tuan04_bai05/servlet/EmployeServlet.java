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
@WebServlet("/employees")
public class EmployeServlet extends HttpServlet {
    @Resource(name = "jdbc/hrdb")
    private DataSource dataSource;

    private EmployeeDAO empDao;
    private DepartmentDAO deptDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        try {
            empDao = new EmployeeDAO(dataSource);
            deptDao = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "List";

        switch (action) {
            case "List":
                // Load toàn bộ employees (không quan tâm deptId)
                List<Employee> allEmployees = empDao.getAllEmployees();
                req.setAttribute("employees", allEmployees);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
            case "departments":
                req.setAttribute("departments", deptDao.getAll());
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                // Lấy employee theo id cho EmployeeDAO nếu muốn edit cụ thể
                // code xử lý lấy employee đưa lên form edit...
                break;
            case "delete":
                empDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("employees");
                break;
            case "view":
                // Hiển thị list all employees (or by department)
                String deptId = req.getParameter("deptId");
                List<Employee> list;
                if (deptId != null) {
                    list = empDao.getAllByDepartment(Integer.parseInt(deptId));
                } else {
                    list = empDao.getAllByDepartment(1); // mặc định dept 1
                }
                req.setAttribute("employees", list);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("departmentId"));

        // Lấy đối tượng Department từ DAO
        Department department = deptDao.getDepartmentById(deptId);

        // Truyền đối tượng Department vào Employee
        Employee emp = new Employee(id, name, department, salary);

        if (id != 0) {
            empDao.update(emp);
        } else {
            empDao.save(emp);
        }

        resp.sendRedirect("employees?deptId=" + deptId);
    }
}