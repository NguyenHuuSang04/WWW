package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.responsitory;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: EmployeeRepository
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findByEmpId(String id);

    List<Employee> findByEmpName(String name);

    List<Employee> findByStatus(int status);

    List<Employee> findByDeptId(String deptId);;
}