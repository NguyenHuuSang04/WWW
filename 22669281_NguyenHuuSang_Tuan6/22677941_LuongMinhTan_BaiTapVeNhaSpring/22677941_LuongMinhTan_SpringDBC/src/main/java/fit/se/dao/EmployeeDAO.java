package fit.se.dao;

import fit.se.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    void save(Employee employee);

    Employee getById(String empId);

    List<Employee> getAll();

    void update(Employee employee);

    void deleteById(String empId);

    // Tìm kiếm theo tên (LIKE)
    List<Employee> findByName(String name);

    // Tìm kiếm theo tuổi (bằng hoặc trong khoảng)
    List<Employee> findByAge(int age);

    List<Employee> findByAgeRange(int minAge, int maxAge);

    // Tìm kiếm theo lương (trên, dưới, khoảng)
    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findBySalaryLessThan(double salary);

    List<Employee> findBySalaryBetween(double minSalary, double maxSalary);
}
