package fit.se.luongminhtan_springjpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document(collection = "employees")
public class Employee {
    @Id
    @JsonIgnore // ẩn không trả về cho client do Mongo quản lý, nhưng không expose ra ngoài)
    private String id;// Mongodb generate _id
    private String empId;
    private String empName;
    private String email;
    private int age;
    private Double salary;
    private int status;
    private String deptId;

    // Constructors
    public Employee() {
    }

    public Employee(String empId, String empName, String email, int age, Double salary, int status, String deptId) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.status = status;
        this.deptId = deptId;
    }
// Getters & Setters
}