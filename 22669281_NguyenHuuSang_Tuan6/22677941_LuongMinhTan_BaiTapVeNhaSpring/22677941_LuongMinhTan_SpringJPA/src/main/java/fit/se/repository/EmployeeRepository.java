package fit.se.repository;

import fit.se.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    // Tìm kiếm theo tên (gần đúng, không phân biệt hoa thường)
    List<Employee> findByEmpNameContainingIgnoreCase(String name);

    // Tìm kiếm theo tuổi
    List<Employee> findByAge(int age);

    // Tìm kiếm theo lương lớn hơn
    List<Employee> findBySalaryGreaterThan(double salary);

    // Tìm kiếm theo lương trong khoảng
    List<Employee> findBySalaryBetween(double min, double max);

    // Lấy danh sách nhân viên theo mã phòng ban (1-n)
    List<Employee> findByDeptId_DeptId(String deptId);
}