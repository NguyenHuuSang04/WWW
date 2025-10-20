package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: Product
 * @Tạo vào ngày: 10/20/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private double price;
    private boolean inStock;
    private List<Comment> comments;
}