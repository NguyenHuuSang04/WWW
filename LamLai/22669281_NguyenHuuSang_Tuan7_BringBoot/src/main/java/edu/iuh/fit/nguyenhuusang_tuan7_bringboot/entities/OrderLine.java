package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: OrderLine
 * @Tạo vào ngày: 10/20/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Entity
@Table(name = "orderline")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    private Product product;
    private int amount;
    private double purchasePrice;
}