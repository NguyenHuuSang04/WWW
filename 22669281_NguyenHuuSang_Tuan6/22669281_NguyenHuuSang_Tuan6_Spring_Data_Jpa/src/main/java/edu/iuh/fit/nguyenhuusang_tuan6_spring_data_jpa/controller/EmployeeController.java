package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.controller;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Employee;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_Jpa
 * @Class: EmployeeController
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Employee add(@RequestBody Employee e) {
        return service.save(e);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee e) {
        e.setEmpId(id);
        return service.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    // Hiển thị nhân viên theo phòng ban (1-n)
    @GetMapping("/department/{deptId}")
    public List<Employee> findByDeptId_DeptId(@PathVariable String deptId) {
        return service.findByDeptId_DeptId(deptId);
    }

    // Tìm kiếm
    @GetMapping("/search/name")
    public List<Employee> findEmployeeByEmpName(@RequestParam String name) {
        return service.findEmployeeByEmpName(name);
    }

    @GetMapping("/search/age")
    public List<Employee> findByAge(@RequestParam int age) {
        return service.findByAge(age);
    }

    @GetMapping("/search/salary-range")
    public List<Employee> findBySalaryBetween(@RequestParam double min, @RequestParam double max) {
        return service.findBySalaryBetween(min, max);
    }

    @GetMapping("/search/salary-greater")
    public List<Employee> findBySalaryGreaterThan(@RequestParam double salary) {
        return service.findBySalaryGreaterThan(salary);
    }
}