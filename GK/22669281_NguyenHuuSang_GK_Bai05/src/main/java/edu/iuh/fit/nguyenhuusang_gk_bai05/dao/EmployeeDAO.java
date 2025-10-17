package edu.iuh.fit.nguyenhuusang_gk_bai05.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai05.model.Department;
import edu.iuh.fit.nguyenhuusang_gk_bai05.model.Employee;
import edu.iuh.fit.nguyenhuusang_gk_bai05.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai05
 * @Class: EmployeeDAO
 * @Tạo vào ngày: 9/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class EmployeeDAO {
    private DBUtil dbUtil;
    private DepartmentDAO departmentDAO; // chưa chắc đúng

    public EmployeeDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
        departmentDAO = new DepartmentDAO(dataSource);
    }

    //read ALL
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection connection = dbUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");

                String departmentID = rs.getString("DEPARTMENT_ID");
                Department department = departmentDAO.getDepartmentByID(departmentID);

                Double salary = rs.getDouble("SALARY");
                Employee employee = new Employee(id, name, department, salary);
                list.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // Read by ID
    public Employee getEmployeeByID(String employeeID) {
        String sql = "SELECT * FROM employees WHERE id=?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employeeID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String name = resultSet.getString("NAME");

                    String departmentID = resultSet.getString("ID");
                    Department department = departmentDAO.getDepartmentByID(departmentID);

                    Double salary = resultSet.getDouble("SALARY");
                    Employee employee = new Employee(id, name, department, salary);

                    return employee;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    //Read employ by departmentID
    public List<Employee> getEmployeeByDepartmentID(String departmentID) {
        String sql = "SELECT * FROM employees WHERE department_id=?";
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, departmentID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String name = resultSet.getString("NAME");

                    String departID = resultSet.getString("ID");
                    Department department = departmentDAO.getDepartmentByID(departID);

                    Double salary = resultSet.getDouble("SALARY");
                    Employee employee = new Employee(id, name, department, salary);

                    employeeList.add(employee);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employeeList;
    }


    public void saveEmployee(Employee employee) {
        String sql = "INSERT INTO employees(name, department_id, salary) VALUES (?,?,?)";

        try(Connection connection = dbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDepartments().getId());
            preparedStatement.setDouble(3, employee.getSalary());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        // update
    public void updateEmployee(Employee employee) {
        double salary = employee.getSalary();
        String sql = "UPDATE employees SET name=?, department_id=?, salary=? WHERE id=?";

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDepartments().getId());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setString(4, employee.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // remove
    public void removeEmployee(String employID) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection connection = dbUtil.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}