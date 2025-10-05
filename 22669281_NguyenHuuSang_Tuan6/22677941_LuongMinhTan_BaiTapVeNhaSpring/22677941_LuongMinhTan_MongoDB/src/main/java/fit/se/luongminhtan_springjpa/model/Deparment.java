package fit.se.luongminhtan_springjpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Document(collection = "departments")
public class Deparment {
    @Id
    @JsonIgnore
    private String _id;
    private String deptId;
    private Set<Employee> employees;
    private String deptName;
    private String name;

}
