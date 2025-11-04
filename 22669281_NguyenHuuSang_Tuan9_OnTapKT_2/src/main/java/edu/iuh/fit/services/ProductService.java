package edu.iuh.fit.services;

import edu.iuh.fit.entities.Product;
import edu.iuh.fit.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT_2
 * @Class: ProductService
 * @Tạo vào ngày: 11/3/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //CRUD
    public List<Product> getAllProduct() {
        return  productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.getProductsByNameContainingIgnoreCase(name);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
         productRepository.deleteById(id);
    }
}