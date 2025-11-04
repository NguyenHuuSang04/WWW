package edu.iuh.fit.controller;

import edu.iuh.fit.entities.Product;
import edu.iuh.fit.entities.ProductCondition;
import edu.iuh.fit.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan9_OnTapKT_2
 * @Class: ProductController
 * @Tạo vào ngày: 11/3/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Value("src/main/resources/static/uploads")
    private String uploadDir;

    @GetMapping
    public String getAllProduct(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "product/products";
    }

    @GetMapping("/search")
    public String searchProductByName(@RequestParam String name, Model model) {
        List<Product> products = productService.getProductsByName(name);
        model.addAttribute("products", products);
        model.addAttribute("keyword", name);
        return "product/products";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable String id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/product-detail";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showFormAdd(Model model) {
        model.addAttribute("product", new Product());
        return "product/product-form";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("fileImage")MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);
            file.transferTo(uploadPath.resolve(fileName));
            product.setImage(fileName);
        }
        productService.save(product);
        return "redirect:/products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable String id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/product-form";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable String id,
                              @ModelAttribute Product product,
                              @RequestParam(value = "fileImage", required = false) MultipartFile file) throws IOException {
        product.setId(id);;

        if(file !=null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);
            file.transferTo(uploadPath.resolve(fileName));
            product.setImage(fileName);
        } else {
            Product exitstingProduct = productService.getProductById(id);
            if(exitstingProduct != null) {
                product.setImage(exitstingProduct.getImage());
            }
        }
        productService.save(product);
        return "redirect:/products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}