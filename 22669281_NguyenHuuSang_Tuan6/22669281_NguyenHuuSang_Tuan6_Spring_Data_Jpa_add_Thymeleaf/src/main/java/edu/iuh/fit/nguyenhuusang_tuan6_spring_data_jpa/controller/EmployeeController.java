package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.controller;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Department;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Employee;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.service.DepartmentService;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private DepartmentService departmentService;

    // Hiển thị danh sách employee
    @GetMapping
    public String viewEmployeeList(Model model) {
        List<Employee> employees = service.getAll();
        model.addAttribute("employees", employees);
        return "employees";
    }

    // Hiển thị chi tiết employee
    @GetMapping("/detail/{id}")
    public String viewEmployeeDetail(@PathVariable String id, Model model) {
        Employee employee = service.getById(id);
        model.addAttribute("employee", employee);
        return "employee-detail";
    }

    // Thêm nhân viên
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAll());
        return "employee-form";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        Department dept = departmentService.getById(employee.getDeptId().getDeptId());
        employee.setDeptId(dept);
        service.save(employee);
        return "redirect:/employees";
    }

    // Sửa nhân viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Employee employee = service.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        return "employee-form";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable String id, @ModelAttribute Employee employee) {
        employee.setEmpId(id);
        Department dept = departmentService.getById(employee.getDeptId().getDeptId());
        employee.setDeptId(dept);
        service.save(employee);
        return "redirect:/employees";
    }

    // Xóa nhân viên
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        service.delete(id);
        return "redirect:/employees";
    }

    // Hiển thị nhân viên theo phòng ban (1-n) - CHỈ GIỮ LẠI method trả về view
    @GetMapping("/department/{deptId}")
    public String listEmployeesByDepartment(@PathVariable String deptId, Model model) {
        List<Employee> employees = service.findByDeptId_DeptId(deptId);
        Department department = departmentService.getById(deptId);
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        return "employees";
    }

    // Tìm kiếm (theo tên)
    @GetMapping("/search")
    public String searchEmployee(
            @RequestParam("type") String type,
            @RequestParam("value") String value,
            Model model
    ) {
        List<Employee> employees;
        switch (type) {
            case "age":
                try {
                    int age = Integer.parseInt(value);
                    employees = service.findByAge(age);
                } catch (NumberFormatException e) {
                    employees = service.getAll();
                }
                break;
            case "salary":
                try {
                    double salary = Double.parseDouble(value);
                    employees = service.findBySalaryGreaterThan(salary);
                } catch (NumberFormatException e) {
                    employees = service.getAll();
                }
                break;
            case "name":
            default:
                employees = service.findEmployeeByEmpName(value);
                break;
        }
        model.addAttribute("employees", employees);
        model.addAttribute("keyword", value);
        model.addAttribute("searchType", type);
        return "employees";
    }

//    @GetMapping("/search/age")
//    public List<Employee> findByAge(@RequestParam int age) {
//        return service.findByAge(age);
//    }
//
//    @GetMapping("/search/salary-range")
//    public List<Employee> findBySalaryBetween(@RequestParam double min, @RequestParam double max) {
//        return service.findBySalaryBetween(min, max);
//    }
//
//    @GetMapping("/search/salary-greater")
//    public List<Employee> findBySalaryGreaterThan(@RequestParam double salary) {
//        return service.findBySalaryGreaterThan(salary);
//    }

}