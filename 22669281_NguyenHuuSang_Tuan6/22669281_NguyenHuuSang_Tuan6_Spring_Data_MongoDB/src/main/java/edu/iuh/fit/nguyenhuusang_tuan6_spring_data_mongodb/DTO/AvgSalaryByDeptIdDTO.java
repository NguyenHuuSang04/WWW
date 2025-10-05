package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.DTO;

import lombok.*;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: AvgSalaryByDeptIdDTO
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvgSalaryByDeptIdDTO {
    private String deptId;
    private Long count;
    private Double avgSalary;
}