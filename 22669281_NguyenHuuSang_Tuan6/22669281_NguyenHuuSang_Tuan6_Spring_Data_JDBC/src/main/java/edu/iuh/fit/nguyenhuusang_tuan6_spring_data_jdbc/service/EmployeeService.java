package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.service;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.dao.EmployeeDAO;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_JDBC
 * @Class: EmployeeService
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }


    public void delete(String id) {
        employeeDAO.delete(id);
    }
    public Employee getEmployeeById(String id) {
        return employeeDAO.getEmployeeById(id);
    }
    public List<Employee> getAllEmployee() {
        return employeeDAO.getAllEmployee();
    }

    // tìm kiếm theo tên
    public List<Employee> findByName(String name) {
        return employeeDAO.findByName(name);
    }
    // tìm tuổi
    public List<Employee> fingByAge(int age) {
        return employeeDAO.fingByAge(age);
    }
    public List<Employee> findByAgeKhoang(int a, int b) {
     return  employeeDAO.findByAgeKhoang(a, b);
    }

    // tìm kiếm theo lương
    public List<Employee> findSalaryLonHon(double salary) {
        return employeeDAO.findSalaryLonHon(salary);
    }
    public List<Employee> findSalaryNhoHon(double salary) {
        return employeeDAO.findSalaryNhoHon(salary);
    }
    public List<Employee> findSalaryKhoang(double a, double b) {
        return employeeDAO.findSalaryKhoang(a, b);
    }
}