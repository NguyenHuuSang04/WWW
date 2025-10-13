package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.controller;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Department;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Employee;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    // Hiển thị danh sách department
    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    // Hiển thị chi tiết department
    @GetMapping("/detail/{id}")
    public String viewDepartmentDetail(@PathVariable String id, Model model) {
        Department department = departmentService.getById(id);
        model.addAttribute("department", department);
        return "department-detail";
    }

    // Thêm department
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department-form";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    // Sửa department
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Department department = departmentService.getById(id);
        model.addAttribute("department", department);
        return "department-form";
    }

    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable String id, @ModelAttribute Department department) {
        department.setDeptId(id);
        departmentService.save(department);
        return "redirect:/departments";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }

    // Tìm kiếm theo tên
    @GetMapping("/search")
    public String searchDepartments(@RequestParam(required = false) String name, Model model) {
        List<Department> departments = (name != null && !name.isEmpty())
                ? departmentService.findByDeptName(name)
                : departmentService.getAll();
        model.addAttribute("departments", departments);
        model.addAttribute("keyword", name);
        return "departments";
    }


}