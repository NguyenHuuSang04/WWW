package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.responsitory;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.DTO.AvgSalaryByDeptIdDTO;
import org.bson.Document;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: EmployeeAnalyticsRepository
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
public interface EmployeeAnalyticsRepository {

    // Đếm số nhân viên theo từng mã phòng ban và tính lương trung bình trong mỗi phòng ban
    List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept();

    // Xuất ra danh sách employee có lương cao nhất
    List<Document> findAllMaxEmployees();

    // Xuất ra danh sách employee lớn tuổi nhất
    List<Document> findAllMaxAgeEmployees();

    // Xuất ra danh sách employee theo mức lương tăng dần từng theo department
    List<Document> getEmployeesSortedBySalaryPerDepartment();
}