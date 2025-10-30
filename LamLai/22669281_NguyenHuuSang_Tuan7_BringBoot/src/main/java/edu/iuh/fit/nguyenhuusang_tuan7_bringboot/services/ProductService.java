package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.services;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.Product;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.ResourceTransactionManager;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: ProductService
 * @Tạo vào ngày: 10/22/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //CRUD
    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    // tìm product theo tên
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}