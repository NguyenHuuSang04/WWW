package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.DTO;

import lombok.*;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: AvgSalaryByDeptIdDTO
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */

// đối tượng dùng để trả về kết quả thống kê số lượng và lương tb theo mã dept
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