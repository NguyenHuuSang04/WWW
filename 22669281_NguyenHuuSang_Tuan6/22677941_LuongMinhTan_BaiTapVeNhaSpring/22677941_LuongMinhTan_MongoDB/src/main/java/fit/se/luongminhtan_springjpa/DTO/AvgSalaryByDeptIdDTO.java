package fit.se.luongminhtan_springjpa.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgSalaryByDeptIdDTO {
    private String deptId;
    private Long count;
    private Double avgSalary;
}