package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.dao.Impl;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.dao.EmployeeDAO;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.model.Department;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_JDBC
 * @Class: EmployeeImpl
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Repository
public class EmployeeImpl implements EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //RowMapper mối quan hệ 1 - n: ánh xạ emp + dept
    private final RowMapper<Employee> rowMapperWithDept = (rs, rowNum) -> {
        Department dept = new Department(
                rs.getString("deptId"),
                rs.getString("deptName")
        );
        return new Employee(
                rs.getString("empId"),
                rs.getString("empName"),
                rs.getString("email"),
                rs.getInt("age"),
                rs.getInt("status"),
                dept,
                rs.getDouble("salary")
        );
    };

    // RowMapper ánh xạ emp cần deptID ( không join )
    private final RowMapper<Employee> rowMapper = (rs, rowNum) -> new Employee(
            rs.getString("empId"),
            rs.getString("empName"),
            rs.getString("email"),
            rs.getInt("age"),
            rs.getInt("status"),
            new Department(rs.getString("deptId"), null),
            rs.getDouble("salary")
    );




    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO Employee(empId, empName, email, age, status, deptId, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                employee.getEmpID(),
                employee.getEmpName(),
                employee.getEmail(),
                employee.getAge(),
                employee.getStatus(),
                employee.getDepartment().getDeptID(),
                employee.getSalary()
        );

    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE Employee SET empName=?, email=?, age=?, status=?, deptId=?, salary=? WHERE empId=?";
        jdbcTemplate.update(sql,
                employee.getEmpName(),
                employee.getEmail(),
                employee.getAge(),
                employee.getStatus(),
                employee.getDepartment().getDeptID(),
                employee.getSalary(),
                employee.getEmpID()
        );

    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Employee WHERE empId = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public Employee getEmployeeById(String id) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.empId = ?";
        return jdbcTemplate.queryForObject(sql, rowMapperWithDept, id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId";
        return jdbcTemplate.query(sql, rowMapperWithDept);
    }

    @Override
    public List<Employee> findByName(String name) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.empName LIKE ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, "%" + name + "%");
    }

    @Override
    public List<Employee> fingByAge(int age) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.age = ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, age);
    }

    @Override
    public List<Employee> findByAgeKhoang(int a, int b) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.age BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, a, b);
    }

    @Override
    public List<Employee> findSalaryLonHon(double salary) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.salary > ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, salary);
    }

    @Override
    public List<Employee> findSalaryNhoHon(double salary) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.salary < ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, salary);
    }

    @Override
    public List<Employee> findSalaryKhoang(double a, double b) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.salary BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, a, b);
    }


}