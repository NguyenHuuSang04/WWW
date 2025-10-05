package fit.se.luongminhtan_springjpa.repository;

import fit.se.luongminhtan_springjpa.DTO.AvgAgeByStatusDTO;
import fit.se.luongminhtan_springjpa.DTO.AvgSalaryByDeptIdDTO;
import org.bson.Document;

import java.util.List;

public interface EmployeeAnalyticsRepository {
    // Đếm số nhân viên và tính lương trung bình theo từng status
    List<AvgAgeByStatusDTO> getCountandAvgAgeByStatus();

    // Đếm số nhân viên theo từng mã phòng ban và tính lương trung bình trong mỗi phòng ban
    List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept();

    // Xuất ra danh sách employee có lương cao nhất
    List<Document> findAllMaxEmployees();

    // Xuất ra danh sách employee lớn tuổi nhất
    List<Document> findAllMaxAgeEmployees();

    // Xuất ra danh sách employee theo mức lương tăng dần từng theo department
    List<Document> getEmployeesSortedBySalaryPerDepartment();
}
