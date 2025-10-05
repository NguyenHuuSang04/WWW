package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.service;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.model.Employee;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.responsitory.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: EmployeeService
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // save , update
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Tìm employee theo empId
    public Employee findByEmpId(String empId) {
        return employeeRepository.findByEmpId(empId);
    }

    // Tìm employee theo Mongo _id
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    // Tìm employee theo tên
    public List<Employee> findByEmpNameContainingIgnoreCase(String name) {
        return employeeRepository.findByEmpName(name);
    }

    // Tìm employee theo status
    public List<Employee> findByStatus(int status) {
        return employeeRepository.findByStatus(status);
    }

    // Tìm employee theo mã phòng ban
    public List<Employee> findByDeptId(String deptId) {
        return employeeRepository.findByDeptId(deptId);
    }

    // Xóa employee theo id (Mongo _id)
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }
}