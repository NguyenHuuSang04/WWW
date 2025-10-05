package fit.se.dao;

import fit.se.model.Employee;
import fit.se.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper ánh xạ Employee + Department (hiển thị mối quan hệ 1-n)
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

    // RowMapper ánh xạ Employee khi chỉ cần deptId (không join)
    private final RowMapper<Employee> rowMapper = (rs, rowNum) -> new Employee(
            rs.getString("empId"),
            rs.getString("empName"),
            rs.getString("email"),
            rs.getInt("age"),
            rs.getInt("status"),
            new Department(rs.getString("deptId"), null),
            rs.getDouble("salary")
    );

    // --- 1. CRUD cơ bản ---

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO Employee(empId, empName, email, age, status, deptId, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                employee.getEmpId(),
                employee.getEmpName(),
                employee.getEmail(),
                employee.getAge(),
                employee.getStatus(),
                employee.getDeptId().getDeptId(),
                employee.getSalary()
        );
    }

    @Override
    public Employee getById(String empId) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.empId = ?";
        return jdbcTemplate.queryForObject(sql, rowMapperWithDept, empId);
    }

    @Override
    public List<Employee> getAll() {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId";
        return jdbcTemplate.query(sql, rowMapperWithDept);
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE Employee SET empName=?, email=?, age=?, status=?, deptId=?, salary=? WHERE empId=?";
        jdbcTemplate.update(sql,
                employee.getEmpName(),
                employee.getEmail(),
                employee.getAge(),
                employee.getStatus(),
                employee.getDeptId().getDeptId(),
                employee.getSalary(),
                employee.getEmpId()
        );
    }

    @Override
    public void deleteById(String empId) {
        String sql = "DELETE FROM Employee WHERE empId = ?";
        jdbcTemplate.update(sql, empId);
    }

    // --- 2. Hiển thị thông tin theo mối quan hệ 1-n (Employee + Department) ---
    // Đã xử lý ở getAll() và getById() với JOIN Department

    // --- 3. Tìm kiếm ---

    // Tìm kiếm theo tên (LIKE)
    @Override
    public List<Employee> findByName(String name) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.empName LIKE ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, "%" + name + "%");
    }

    // Tìm kiếm theo tuổi (bằng hoặc trong khoảng)
    @Override
    public List<Employee> findByAge(int age) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.age = ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, age);
    }

    @Override
    public List<Employee> findByAgeRange(int minAge, int maxAge) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.age BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, minAge, maxAge);
    }

    // Tìm kiếm theo lương (trên, dưới, khoảng)
    @Override
    public List<Employee> findBySalaryGreaterThan(double salary) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.salary > ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, salary);
    }

    @Override
    public List<Employee> findBySalaryLessThan(double salary) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.salary < ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, salary);
    }

    @Override
    public List<Employee> findBySalaryBetween(double minSalary, double maxSalary) {
        String sql = "SELECT e.*, d.deptName FROM Employee e JOIN Department d ON e.deptId = d.deptId WHERE e.salary BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rowMapperWithDept, minSalary, maxSalary);
    }
}