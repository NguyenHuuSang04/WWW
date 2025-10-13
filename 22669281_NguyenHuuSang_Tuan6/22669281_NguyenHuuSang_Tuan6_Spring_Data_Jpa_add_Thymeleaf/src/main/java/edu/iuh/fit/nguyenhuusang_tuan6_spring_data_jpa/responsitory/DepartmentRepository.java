package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.responsitory;

import edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_Jpa
 * @Interface: DepartmentRepository
 * @Tạo vào ngày: 10/6/2025
 * @Tác giả: Nguyen Huu Sang
 */
public interface DepartmentRepository extends JpaRepository<Department, String> {
    List<Department> findByDeptNameContainingIgnoreCase(String name);
}