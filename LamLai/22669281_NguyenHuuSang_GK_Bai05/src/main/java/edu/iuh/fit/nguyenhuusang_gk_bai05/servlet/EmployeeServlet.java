package edu.iuh.fit.nguyenhuusang_gk_bai05.servlet;

import edu.iuh.fit.nguyenhuusang_gk_bai05.dao.DepartmentDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai05.dao.EmployeeDAO;
import edu.iuh.fit.nguyenhuusang_gk_bai05.model.Department;
import edu.iuh.fit.nguyenhuusang_gk_bai05.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
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
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai05
 * @Class: EmployeeServlet
 * @Tạo vào ngày: 9/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet({"/employee", "/employees"})
public class EmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    @Resource(name = "jdbc/employeedb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            employeeDAO = new EmployeeDAO(dataSource);
            departmentDAO = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null) action = "list";

        switch (action) {
            case "list":
                // load toàn bộ employee
                List<Employee> employeeList = employeeDAO.getAllEmployee();
                req.setAttribute("employees", employeeList);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
            case "new":
//                req.setAttribute("departments", departmentDAO.getAllDepartment()); // load danh sách department để chọn
//                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
//                break;

                String deptId = req.getParameter("deptId");
                if(deptId!=null) {
                    Department department = departmentDAO.getDepartmentByID(deptId);
                    req.setAttribute("selectedDept", department); // truyền dept hiện tại sang jsp để hiển thị trên selected
                    req.setAttribute("fixDept", true); // không cho đổi department
                } else {
                    req.setAttribute("departments", departmentDAO.getAllDepartment());
                    req.setAttribute("fixDept", false);;
                }

                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            case "edit":
                String empId = req.getParameter("id");
                Employee employee = employeeDAO.getEmployeeByID(empId);
                List<Department> departments = departmentDAO.getAllDepartment();

                req.setAttribute("employee", employee);
                req.setAttribute("departments", departments);
                req.getRequestDispatcher("employee-form.jsp").forward(req,resp);
                break;

            case "delete":
                employeeDAO.removeEmployee(req.getParameter("id"));
                resp.sendRedirect("employees");
                break;
            case "employees":
                String deptId_2= req.getParameter("deptId");
                List<Employee> employeeList_2 = employeeDAO.getEmployeeByDepartmentID(deptId_2);
                req.setAttribute("employees", employeeList_2);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;

            case "viewbyid": // làm gì??
                String deptID = req.getParameter("deptID");
                List<Employee> list;

                if (deptID!=null) {
                    list = employeeDAO.getEmployeeByDepartmentID(deptID);
                } else {
                    list = employeeDAO.getEmployeeByDepartmentID("1");
                }

                req.setAttribute("employees", list);
                req.setAttribute("departments", departmentDAO.getAllDepartment());
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
        }
    }


    // thêm, update
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String idEmpParam = req.getParameter("id");
        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        String deptId = req.getParameter("departmentId");
        Department department = departmentDAO.getDepartmentByID(deptId);

        Employee employee = new Employee(idEmpParam,name, department, salary);

        if(idEmpParam != null && !idEmpParam.isEmpty()) {
            employeeDAO.updateEmployee(employee);
        } else {
            employeeDAO.saveEmployee(employee);
        }
        resp.sendRedirect(req.getContextPath() + "/employees");
    }
}