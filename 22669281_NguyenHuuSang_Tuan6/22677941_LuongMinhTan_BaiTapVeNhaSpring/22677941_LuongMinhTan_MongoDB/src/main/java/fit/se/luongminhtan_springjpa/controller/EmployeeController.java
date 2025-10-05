package fit.se.luongminhtan_springjpa.controller;



import fit.se.luongminhtan_springjpa.DTO.AvgAgeByStatusDTO;
import fit.se.luongminhtan_springjpa.DTO.AvgSalaryByDeptIdDTO;
import fit.se.luongminhtan_springjpa.model.Employee;
import fit.se.luongminhtan_springjpa.service.EmployeeAnalyticsService;
import fit.se.luongminhtan_springjpa.service.EmployeeService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeAnalyticsService employeeAnalyticsService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeAnalyticsService employeeAnalyticsService) {
        this.employeeService = employeeService;
        this.employeeAnalyticsService = employeeAnalyticsService;
    }

    // CRUD APIs
    @PostMapping
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Optional<Employee> employee = employeeService.findById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable String id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployeeByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(employeeService.findByEmpNameContainingIgnoreCase(name));
    }

    @GetMapping("/by-empid/{empId}")
    public ResponseEntity<Employee> getEmployeeByEmpId(@PathVariable String empId) {
        Employee emp = employeeService.findByEmpId(empId);
        if (emp != null) return ResponseEntity.ok(emp);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<Employee>> getEmployeesByStatus(@PathVariable int status) {
        return ResponseEntity.ok(employeeService.findByStatus(status));
    }

    @GetMapping("/by-dept/{deptId}")
    public ResponseEntity<List<Employee>> getEmployeesByDeptId(@PathVariable String deptId) {
        return ResponseEntity.ok(employeeService.findByDeptId(deptId));
    }

    // ANALYTICS APIs

    // 1. Employees có salary cao nhất
    @GetMapping("/max-salary")
    public ResponseEntity<List<Document>> getEmployeesWithMaxSalary() {
        return ResponseEntity.ok(employeeAnalyticsService.findAllMaxEmployees());
    }

    // 2. Employees lớn tuổi nhất
    @GetMapping("/max-age")
    public ResponseEntity<List<Document>> getEmployeesWithMaxAge() {
        return ResponseEntity.ok(employeeAnalyticsService.findAllMaxAgeEmployees());
    }

    // 3. Thống kê số employee và lương trung bình theo phòng ban
    @GetMapping("/analytics/dept")
    public ResponseEntity<List<AvgSalaryByDeptIdDTO>> getEmployeeCountAndAvgSalaryByDept() {
        return ResponseEntity.ok(employeeAnalyticsService.getCountandAvgSalaryByDept());
    }

    // 4. Thống kê số employee và tuổi trung bình theo status
    @GetMapping("/analytics/status")
    public ResponseEntity<List<AvgAgeByStatusDTO>> getEmployeeCountAndAvgAgeByStatus() {
        return ResponseEntity.ok(employeeAnalyticsService.getCountandAvgAgeByStatus());
    }

    // 5. Danh sách employees theo lương tăng dần trong từng phòng ban
    @GetMapping("/sorted-by-salary")
    public ResponseEntity<List<Document>> getEmployeesSortedBySalaryPerDepartment() {
        return ResponseEntity.ok(employeeAnalyticsService.getEmployeesSortedBySalaryPerDepartment());
    }
}