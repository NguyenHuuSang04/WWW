package iuh.fit.nguyenhuusang_tuan04_bai05.dao;

import iuh.fit.nguyenhuusang_tuan04_bai05.model.Department;
import iuh.fit.nguyenhuusang_tuan04_bai05.model.Employee;
import iuh.fit.nguyenhuusang_tuan04_bai05.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DBUtil dbutil;
    private DepartmentDAO departmentDAO;

    public EmployeeDAO(DataSource dataSource, DepartmentDAO departmentDAO) {
        dbutil = new DBUtil(dataSource);
        this.departmentDAO = departmentDAO;
    }

    public EmployeeDAO(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> emplist = new ArrayList<>();
        String sql = "select * from employees";
        try (
                Connection conn = dbutil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                int deptId = rs.getInt("department_id");
                Department dept = departmentDAO.getDepartmentById(deptId);
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        dept,
                        rs.getDouble("salary")
                );
                emplist.add(emp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return emplist;
    }

    public List<Employee> getAllByDepartment(int deptId) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id=?";
        try (
                Connection conn = dbutil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, deptId);
            try (ResultSet rs = ps.executeQuery()) {
                Department dept = departmentDAO.getDepartmentById(deptId);
                while (rs.next()) {
                    Employee emp = new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            dept, // same department object for this list
                            rs.getDouble("salary")
                    );
                    list.add(emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Employee emp) {
        String sql = "INSERT INTO employees(name, salary, department_id) VALUES (?,?,?)";
        try (Connection conn = dbutil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setInt(3, emp.getDepartment().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Employee emp) {
        String sql = "UPDATE employees SET name=?, salary=?, department_id=? WHERE id=?";
        try (Connection conn = dbutil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setInt(3, emp.getDepartment().getId());
            ps.setInt(4, emp.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = dbutil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}