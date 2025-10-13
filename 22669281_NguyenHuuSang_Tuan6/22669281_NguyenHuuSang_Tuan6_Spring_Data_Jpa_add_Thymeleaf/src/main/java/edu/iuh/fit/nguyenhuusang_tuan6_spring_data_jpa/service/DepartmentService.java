package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.service;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Department;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Employee;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.responsitory.DepartmentRepository;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.responsitory.EmployeeReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_Jpa
 * @Class: DepartmentService
 * @Tạo vào ngày: 10/6/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class DepartmentService {
    @Autowired
    private

    DepartmentRepository repository;
    EmployeeReponsitory employeeReponsitory;

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Department save(Department d) {
        return repository.save(d);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<Department> findByDeptName(String name) {
        return repository.findByDeptNameContainingIgnoreCase(name);
    }
    public List<Employee> findEmployeesByDeptId(String deptId) {
        return employeeReponsitory.findByDeptId_DeptId(deptId);
    }
}

