package fit.se.service;

import fit.se.dao.EmployeeDAO;
import fit.se.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // CRUD cơ bản
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    public Employee getById(String empId) {
        return employeeDAO.getById(empId);
    }

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    public void deleteById(String empId) {
        employeeDAO.deleteById(empId);
    }

    // Các chức năng nâng cao

    // Tìm kiếm theo tên
    public List<Employee> findByName(String name) {
        return employeeDAO.findByName(name);
    }

    // Tìm theo tuổi
    public List<Employee> findByAge(int age) {
        return employeeDAO.findByAge(age);
    }

    public List<Employee> findByAgeRange(int minAge, int maxAge) {
        return employeeDAO.findByAgeRange(minAge, maxAge);
    }

    // Tìm kiếm theo lương
    public List<Employee> findBySalaryGreaterThan(double salary) {
        return employeeDAO.findBySalaryGreaterThan(salary);
    }

    public List<Employee> findBySalaryLessThan(double salary) {
        return employeeDAO.findBySalaryLessThan(salary);
    }

    public List<Employee> findBySalaryBetween(double minSalary, double maxSalary) {
        return employeeDAO.findBySalaryBetween(minSalary, maxSalary);
    }
}