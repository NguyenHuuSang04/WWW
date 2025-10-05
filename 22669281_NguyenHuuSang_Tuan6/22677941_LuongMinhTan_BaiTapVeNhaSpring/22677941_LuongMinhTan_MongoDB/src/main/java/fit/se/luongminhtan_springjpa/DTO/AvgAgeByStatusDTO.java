package fit.se.luongminhtan_springjpa.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgAgeByStatusDTO {
    private Integer status;
    private Long count;
    private Double avgAge;
}