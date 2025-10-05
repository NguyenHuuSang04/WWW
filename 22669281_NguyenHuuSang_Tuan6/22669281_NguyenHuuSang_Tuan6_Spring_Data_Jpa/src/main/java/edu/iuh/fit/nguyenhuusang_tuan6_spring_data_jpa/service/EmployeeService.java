package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.service;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Employee;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.responsitory.EmployeeReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_Jpa
 * @Class: EmployeeService
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeReponsitory reponsitory;

    public List<Employee> getAll() {
        return reponsitory.findAll();
    }

    public Employee getById(String id) {
        return reponsitory.findById(id).orElse(null);
    }

    public Employee save(Employee e) {
        return reponsitory.save(e);
    }

    public void delete(String id) {
        reponsitory.deleteById(id);
    }

    // tìm emp by empName
    public List<Employee> findEmployeeByEmpName(String empName) {
        return  reponsitory.findEmployeeByEmpName(empName);
    }

    public List<Employee> findByAge(int age) {
        return  reponsitory.findByAge(age);
    }

    // tìm kiếm lương lớn hơn
    public List<Employee> findBySalaryGreaterThan(double salary) {
        return  reponsitory.findBySalaryGreaterThan(salary);
    }

    public List<Employee> findBySalaryBetween(double salary_1, double salary_2) {
        return  reponsitory.findBySalaryBetween(salary_1, salary_2);
    }

    // lấy danh sách nv theo mã phòng ban (1-n)
    public List<Employee> findByDeptId_DeptId(String deptId) {
        return  reponsitory.findByDeptId_DeptId(deptId);
    }

}