package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_jpa.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_Jpa
 * @Class: Department
 * @Tạo vào ngày: 10/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "deptId")
public class Department {
    @Id
    @Column(name = "dept_id")
    private String deptId;

    @Column(name = "dept_name")
    private String deptName;

    @OneToMany(mappedBy = "deptId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employee> employees;
}