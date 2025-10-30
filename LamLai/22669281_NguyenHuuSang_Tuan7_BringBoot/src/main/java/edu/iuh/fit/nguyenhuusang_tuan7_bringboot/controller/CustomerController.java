package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.controller;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.Customer;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: CustomerController
 * @Tạo vào ngày: 10/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping// dùng requestMapping mặc định ở trên
    public String ListCustomer(Model model) {
        List<Customer> customers = customerService.findAllCustomer();
        model.addAttribute("customers", customers);
        return "customer/customers";
    }

    // tìm kiếm khách hàng theo tên
    @GetMapping("/search") // bổ sung thêm đuôi / search cho requestMapping ở trên --> /customers/search
    //@PathVariable: lấy giá trị từ 1 phần của URL: customers/detail/1 --> lấy 1
    // @RequestParam: lấy giá trị tham số lọc ( không phải 1 phần của URL ): /search?name=C --> lấy "C"
    public String searchCustomersByName(@RequestParam String name, Model model) {
        List<Customer> customers = customerService.findCustomerByName(name);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", name);
        return "customer/customers";
    }

    @GetMapping("/detail/{id}")
    // xem chi tiết khách hàng
    public String searchCustomerById(@PathVariable Integer id, Model model) {
        Customer customer = customerService.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/customer-detail";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomer(@PathVariable Integer id, Model model) {
        Customer customer = customerService.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/customer-form";
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable Integer id, @ModelAttribute Customer customer) {
        customer.setId(id);
        System.out.println("Customer name: " + customer.getName());
        System.out.println("Customer since: " + customer.getCustomerSince());
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    // xóa khách hàng
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

}