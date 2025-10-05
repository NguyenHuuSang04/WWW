package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.dao;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jdbc.model.Employee;

import java.util.List;


/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_JDBC
 * @Class: EmployeeDAO
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
public interface EmployeeDAO {
    void save(Employee employee);
    void update(Employee employee);
    void delete(String id);
    Employee getEmployeeById(String id);
    List<Employee> getAllEmployee();

    // tìm kiếm theo tên
    List<Employee> findByName(String name);

    // tìm tuổi
    List<Employee> fingByAge(int age);
    List<Employee> findByAgeKhoang(int a, int b);

    // tìm kiếm theo lương
    List<Employee> findSalaryLonHon(double salary);
    List<Employee> findSalaryNhoHon(double salary);
    List<Employee> findSalaryKhoang(double a, double b);


}