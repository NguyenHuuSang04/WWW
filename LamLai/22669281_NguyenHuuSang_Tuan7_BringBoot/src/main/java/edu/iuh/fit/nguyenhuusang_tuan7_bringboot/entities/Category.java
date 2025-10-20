package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: Category
 * @Tạo vào ngày: 10/20/2025
 * @Tác giả: Nguyen Huu Sang
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int id;
    private String name;
}