package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.responsitory.Impl;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.DTO.AvgSalaryByDeptIdDTO;
import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.responsitory.EmployeeAnalyticsRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: EmployeeAnalyticsRepositoryImpl
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
// thực thi phương thức analytics sử dụng aggregation pipeline cảu mongodb
@Repository
public class EmployeeAnalyticsRepositoryImpl implements EmployeeAnalyticsRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public EmployeeAnalyticsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

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

    // list emp có lương cao nhất
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