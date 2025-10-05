package fit.se.sevice;

import fit.se.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fit.se.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    // CRUD
    public List<Employee> getAll() { return repo.findAll(); }
    public Employee getById(String id) { return repo.findById(id).orElse(null); }
    public Employee save(Employee e) { return repo.save(e); }
    public void delete(String id) { repo.deleteById(id); }

    // Hiển thị 1-n
    public List<Employee> getByDepartment(String deptId) {
        return repo.findByDeptId_DeptId(deptId);
    }

    // Tìm kiếm
    public List<Employee> searchByName(String name) { return repo.findByEmpNameContainingIgnoreCase(name); }
    public List<Employee> searchByAge(int age) { return repo.findByAge(age); }
    public List<Employee> searchBySalaryRange(double min, double max) { return repo.findBySalaryBetween(min, max); }
    public List<Employee> searchBySalaryGreater(double salary) { return repo.findBySalaryGreaterThan(salary); }
}