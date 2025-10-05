package fit.se.luongminhtan_springjpa.repository;

import fit.se.luongminhtan_springjpa.DTO.AvgAgeByStatusDTO;
import fit.se.luongminhtan_springjpa.DTO.AvgSalaryByDeptIdDTO;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Repository;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class EmployeeAnalyticsRepositoryImpl implements EmployeeAnalyticsRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public EmployeeAnalyticsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Đếm số nhân viên và tính lương trung bình theo từng status
    @Override
    public List<AvgAgeByStatusDTO> getCountandAvgAgeByStatus() {
        Aggregation agg = newAggregation(
                group("status")
                        .count().as("count")
                        .avg("age").as("avgAge"),
                project("count", "avgAge")
                        .and("_id").as("status")
        );
        AggregationResults<AvgAgeByStatusDTO> results = mongoTemplate.aggregate(agg, "employees", AvgAgeByStatusDTO.class);
        return results.getMappedResults();
    }

    // Đếm số nhân viên theo từng mã phòng ban và tính lương trung bình trong mỗi phòng ban
    @Override
    public List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept() {
        Aggregation agg = newAggregation(
                group("deptId")
                        .count().as("count")
                        .avg("salary").as("avgSalary"),
                project("count", "avgSalary")
                        .and("_id").as("deptId")
        );
        AggregationResults<AvgSalaryByDeptIdDTO> results = mongoTemplate.aggregate(agg, "employees", AvgSalaryByDeptIdDTO.class);
        return results.getMappedResults();
    }

    // Xuất ra danh sách employee có lương cao nhất
    @Override
    public List<Document> findAllMaxEmployees() {
        Aggregation agg = newAggregation(
                group().max("salary").as("maxSalary"),
                lookup("employees", "maxSalary", "salary", "employees"),
                unwind("employees"),
                replaceRoot("employees")
        );
        AggregationResults<Document> maxSalaryResults = mongoTemplate.aggregate(agg, "employees", Document.class);
        return maxSalaryResults.getMappedResults();
    }

    // Xuất ra danh sách employee lớn tuổi nhất
    @Override
    public List<Document> findAllMaxAgeEmployees() {
        Aggregation agg = newAggregation(
                group().max("age").as("maxAge"),
                lookup("employees", "maxAge", "age", "employees"),
                unwind("employees"),
                replaceRoot("employees")
        );
        AggregationResults<Document> maxAgeResults = mongoTemplate.aggregate(agg, "employees", Document.class);
        return maxAgeResults.getMappedResults();
    }

    // Xuất ra danh sách employee theo mức lương tăng dần từng theo department
    @Override
    public List<Document> getEmployeesSortedBySalaryPerDepartment() {
        Aggregation agg = newAggregation(
                sort(Sort.Direction.ASC, "deptId", "salary"),
                lookup("departments", "deptId", "deptId", "department"),
                unwind("department", true),
                project("empId", "empName", "salary", "deptId")
                        .and("department.deptName").as("deptName")
        );
        AggregationResults<Document> sortedResults = mongoTemplate.aggregate(agg, "employees", Document.class);
        return sortedResults.getMappedResults();
    }
}