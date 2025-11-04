package edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT_2
 * @Class: Product
 * @Tạo vào ngày: 11/3/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private BigDecimal price;
    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    //checkBox
    private boolean inStock;

    //Radio
    @Enumerated(EnumType.STRING)
    private ProductCondition productCondition;

    //Select multipte
    @ElementCollection
    @CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "color")
    private List<String> colors;




}