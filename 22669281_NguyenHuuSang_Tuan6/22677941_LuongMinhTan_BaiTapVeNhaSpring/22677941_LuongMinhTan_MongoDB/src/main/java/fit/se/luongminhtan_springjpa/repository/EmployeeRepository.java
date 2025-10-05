package fit.se.luongminhtan_springjpa.repository;



import fit.se.luongminhtan_springjpa.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {


    // Tìm nhân viên theo empId
    Employee findByEmpId(String empId);

    // Tìm nhân viên theo tên (không phân biệt hoa thường, chứa chuỗi)
    List<Employee> findByEmpNameContainingIgnoreCase(String name);

    // Tìm nhân viên theo trạng thái (status)
    List<Employee> findByStatus(int status);

    // Lấy danh sách nhân viên theo mã phòng ban
    List<Employee> findByDeptId(String deptId);
}