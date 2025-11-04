package edu.iuh.fit.repositories;

import edu.iuh.fit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
    * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT_2
    * @Interface: ProductRepository
    * @Tạo vào ngày: 11/3/2025
    * @Tác giả: Nguyen Huu Sang
*/
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> getProductsByNameContainingIgnoreCase(String name);

    Product findProductById(String id);

    Product getProductById(String id);
}