package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_Jpa
 * @Class: Employee
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
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