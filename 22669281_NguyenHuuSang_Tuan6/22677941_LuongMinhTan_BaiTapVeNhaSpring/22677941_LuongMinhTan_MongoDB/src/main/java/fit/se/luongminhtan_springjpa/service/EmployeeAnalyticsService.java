package fit.se.luongminhtan_springjpa.service;



import fit.se.luongminhtan_springjpa.DTO.AvgAgeByStatusDTO;
import fit.se.luongminhtan_springjpa.DTO.AvgSalaryByDeptIdDTO;
import fit.se.luongminhtan_springjpa.repository.EmployeeAnalyticsRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeeAnalyticsService {

    private final EmployeeAnalyticsRepository employeeAnalyticsRepository;

    @Autowired
    public EmployeeAnalyticsService(EmployeeAnalyticsRepository employeeAnalyticsRepository) {
        this.employeeAnalyticsRepository = employeeAnalyticsRepository;
    }

    // Đếm số nhân viên và tuổi trung bình theo status
    public List<AvgAgeByStatusDTO> getCountandAvgAgeByStatus() {
        return employeeAnalyticsRepository.getCountandAvgAgeByStatus();
    }

    // Đếm số nhân viên và lương trung bình theo phòng ban
    public List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept() {
        return employeeAnalyticsRepository.getCountandAvgSalaryByDept();
    }

    // Danh sách nhân viên có lương cao nhất
    public List<Document> findAllMaxEmployees() {
        return employeeAnalyticsRepository.findAllMaxEmployees();
    }

    // Danh sách nhân viên lớn tuổi nhất
    public List<Document> findAllMaxAgeEmployees() {
        return employeeAnalyticsRepository.findAllMaxAgeEmployees();
    }

    // Danh sách nhân viên theo lương tăng dần từng phòng ban
    public List<Document> getEmployeesSortedBySalaryPerDepartment() {
        return employeeAnalyticsRepository.getEmployeesSortedBySalaryPerDepartment();
    }
}