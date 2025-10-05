package edu.iuh.fit.nguyenhuusang_tuan6_spring_data_mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan6_Spring_Data_MongoDB
 * @Class: Department
 * @Tạo vào ngày: 10/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Document(collection = "departments")
public class Department {
    @Id
    @JsonIgnore
    private String _id;
    private String deptId;
    private Set<Employee> employees;
    private String deptName;


}