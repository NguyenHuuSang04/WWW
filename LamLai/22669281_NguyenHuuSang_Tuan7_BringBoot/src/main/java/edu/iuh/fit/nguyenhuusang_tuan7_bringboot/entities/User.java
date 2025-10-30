package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: User
 * @Tạo vào ngày: 10/22/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)// không trùng, không rỗng
    private String username;
    @Column(nullable = false) // không rỗng
    private String password;

    private String role;
}