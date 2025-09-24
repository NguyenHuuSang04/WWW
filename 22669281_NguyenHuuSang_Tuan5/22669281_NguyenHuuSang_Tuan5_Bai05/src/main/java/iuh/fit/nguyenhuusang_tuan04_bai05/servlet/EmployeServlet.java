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
    @Resource(name = "jdbc/tuan04_bai05")
    private DataSource dataSource;

    private EmployeeDAO empDao;
    private DepartmentDAO deptDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        try {
            deptDao = new DepartmentDAO(dataSource);
            empDao = new EmployeeDAO(dataSource, deptDao);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String deptId = req.getParameter("deptId");

        if (action == null) action = "List";

        // Chỉ lọc employees theo deptId nếu action là List hoặc view
        if ((action.equals("List") || action.equals("view")) && deptId != null && !deptId.isEmpty()) {
            List<Employee> list = empDao.getAllByDepartment(Integer.parseInt(deptId));
            req.setAttribute("employees", list);
            req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
            return;
        }

        switch (action) {
            case "List":
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
                Employee emp = empDao.getEmployeeById(id); // Bạn cần có hàm này
                List<Department> depts = deptDao.getAll();
                req.setAttribute("employee", emp);
                req.setAttribute("departments", depts);
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            case "delete":
                empDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("employees");
                break;
            case "view":
                // Đã xử lý ở đầu hàm (tránh lặp)
                break;
            case "new":
                String deptIdParam = req.getParameter("deptId");
                if (deptIdParam != null && !deptIdParam.isEmpty()) {
                    try {
                        int deptIdInt = Integer.parseInt(deptIdParam);
                        Department dept = deptDao.getDepartmentById(deptIdInt);
                        req.setAttribute("department", dept); // chỉ truyền 1 phòng ban
                        req.setAttribute("fixedDept", true); // báo cho JSP biết là chỉ được dùng 1 phòng ban này
                    } catch (NumberFormatException ex) {
                        List<Department> departments = deptDao.getAll();
                        req.setAttribute("departments", departments);
                        req.setAttribute("fixedDept", false);
                    }
                } else {
                    List<Department> departments = deptDao.getAll();
                    req.setAttribute("departments", departments);
                    req.setAttribute("fixedDept", false);
                }
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            default:
                // Nếu action không hợp lệ, về danh sách tất cả nhân viên
                List<Employee> defaultEmployees = empDao.getAllEmployees();
                req.setAttribute("employees", defaultEmployees);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
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