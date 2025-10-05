package fit.se.controller;

import fit.se.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fit.se.sevice.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    // CRUD
    @GetMapping
    public List<Employee> getAll() { return service.getAll(); }
    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id) { return service.getById(id); }
    @PostMapping
    public Employee add(@RequestBody Employee e) { return service.save(e); }
    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee e) {
        e.setEmpId(id);
        return service.save(e);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) { service.delete(id); }

    // Hiển thị nhân viên theo phòng ban (1-n)
    @GetMapping("/department/{deptId}")
    public List<Employee> getByDepartment(@PathVariable String deptId) {
        return service.getByDepartment(deptId);
    }

    // Tìm kiếm
    @GetMapping("/search/name")
    public List<Employee> searchByName(@RequestParam String name) {
        return service.searchByName(name);
    }

    @GetMapping("/search/age")
    public List<Employee> searchByAge(@RequestParam int age) {
        return service.searchByAge(age);
    }

    @GetMapping("/search/salary-range")
    public List<Employee> searchBySalaryRange(@RequestParam double min, @RequestParam double max) {
        return service.searchBySalaryRange(min, max);
    }

    @GetMapping("/search/salary-greater")
    public List<Employee> searchBySalaryGreater(@RequestParam double salary) {
        return service.searchBySalaryGreater(salary);
    }
}