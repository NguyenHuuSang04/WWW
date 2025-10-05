package fit.se.controller;

import fit.se.model.Employee;
import fit.se.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Lấy tất cả employee (có thông tin phòng ban)
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    // Lấy employee theo id
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeService.getById(id);
    }

    // Thêm mới employee
    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    // Sửa employee
    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        employee.setEmpId(id);
        employeeService.update(employee);
    }

    // Xóa employee
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteById(id);
    }

    // --- Tìm kiếm nâng cao ---

    // Tìm theo tên (LIKE)
    @GetMapping("/search/name")
    public List<Employee> findByName(@RequestParam String name) {
        return employeeService.findByName(name);
    }

    // Tìm theo tuổi chính xác
    @GetMapping("/search/age")
    public List<Employee> findByAge(@RequestParam int age) {
        return employeeService.findByAge(age);
    }

    // Tìm theo khoảng tuổi
    @GetMapping("/search/age-range")
    public List<Employee> findByAgeRange(@RequestParam int min, @RequestParam int max) {
        return employeeService.findByAgeRange(min, max);
    }

    // Tìm theo lương lớn hơn
    @GetMapping("/search/salary/greater")
    public List<Employee> findBySalaryGreater(@RequestParam double salary) {
        return employeeService.findBySalaryGreaterThan(salary);
    }

    // Tìm theo lương nhỏ hơn
    @GetMapping("/search/salary/less")
    public List<Employee> findBySalaryLess(@RequestParam double salary) {
        return employeeService.findBySalaryLessThan(salary);
    }

    // Tìm theo khoảng lương
    @GetMapping("/search/salary-range")
    public List<Employee> findBySalaryBetween(@RequestParam double min, @RequestParam double max) {
        return employeeService.findBySalaryBetween(min, max);
    }
}