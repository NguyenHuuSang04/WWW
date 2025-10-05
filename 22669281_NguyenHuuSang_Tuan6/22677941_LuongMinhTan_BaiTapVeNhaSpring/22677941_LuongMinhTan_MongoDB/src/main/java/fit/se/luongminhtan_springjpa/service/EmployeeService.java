package fit.se.luongminhtan_springjpa.service;

import fit.se.luongminhtan_springjpa.model.Employee;
import fit.se.luongminhtan_springjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Tạo mới hoặc cập nhật thông tin employee
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Lấy tất cả employee
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

    // Tìm employee theo tên (không phân biệt hoa thường)
    public List<Employee> findByEmpNameContainingIgnoreCase(String name) {
        return employeeRepository.findByEmpNameContainingIgnoreCase(name);
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