package fit.se.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "empId")
public class Employee {
    @Id
    @Column(name = "emp_id")
    private String empId;

    @Column(name = "emp_name")
    private String empName;

    private String email;
    private int age;
    private int status;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department deptId;
}