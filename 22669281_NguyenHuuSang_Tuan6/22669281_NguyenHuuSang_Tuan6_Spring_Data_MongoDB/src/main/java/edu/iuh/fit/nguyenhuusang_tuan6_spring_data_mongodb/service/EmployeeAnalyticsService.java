package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.service;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.DTO.AvgSalaryByDeptIdDTO;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.responsitory.EmployeeAnalyticsRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: EmployeeAnalyticsService
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class EmployeeAnalyticsService {
    private final EmployeeAnalyticsRepository employeeAnalyticsRepository;

    @Autowired
    public EmployeeAnalyticsService(EmployeeAnalyticsRepository employeeAnalyticsRepository) {
        this.employeeAnalyticsRepository = employeeAnalyticsRepository;
    }


    public List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept() {
        return employeeAnalyticsRepository.getCountandAvgSalaryByDept();
    }


    public List<Document> findAllMaxEmployees() {
        return employeeAnalyticsRepository.findAllMaxEmployees();
    }


    public List<Document> findAllMaxAgeEmployees() {
        return employeeAnalyticsRepository.findAllMaxAgeEmployees();
    }


    public List<Document> getEmployeesSortedBySalaryPerDepartment() {
        return employeeAnalyticsRepository.getEmployeesSortedBySalaryPerDepartment();
    }
}