package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.responsitory;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_Jpa
 * @Class: EmployeeReponsitory
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */

// định nghĩa các phương thức truy vấn, sư dụng spring data jpa
// không cần tự implement sql, chỉ cần đặt tên đúng quy ước là đc spring data jpa tự sinh code truy vấn
public interface EmployeeReponsitory  extends JpaRepository<Employee, String> {
    List<Employee> findEmployeeByEmpName(String empName);

    List<Employee> findByAge(int age);

    // tìm kiếm lương lớn hơn
    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findBySalaryBetween(double salary_1, double salary_2);

    // lấy danh sách nv theo mã phòng ban (1-n)
    List<Employee> findByDeptId_DeptId(String deptId);

}