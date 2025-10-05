package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: Employee
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Getter
@Setter
@Document(collection = "employees")
public class Employee {
    @Id
    @JsonIgnore
    private String id;// Mongodb generate _id
    private String empId;
    private String empName;
    private String email;
    private int age;
    private Double salary;
    private int status;
    private String deptId;

    public Employee() {
    }

    public Employee(String id, String empId, String empName, String email, int age, Double salary, int status, String deptId) {
        this.id = id;
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.status = status;
        this.deptId = deptId;
    }
}