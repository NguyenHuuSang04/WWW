package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.services;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.Customer;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.Order;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities.CustomerRepository;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: CustomerService
 * @Tạo vào ngày: 10/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    //CRUD
    public List<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }

    // find by Id
    public Customer findCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }

    // save, update
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //delete
    public void deleteCustomer(Integer id) {
        // lấy danh sách Orders liên quan đến Customer
        List<Order> orders = orderRepository.findByCustomer_Id(id);

        // đặt customer của mỗi order thành null
        for (Order order :orders) {
            order.setCustomer(null);
            orderRepository.save(order); // lưu lại sau khi sửa
        }
        customerRepository.deleteById(id);
    }

    // find by name
    public  List<Customer> findCustomerByName(String name) {
        return customerRepository.findCustomerByNameContainingIgnoreCase(name);
    }

    public Customer findByPhone(String phone) {
        return customerRepository.findCustomerByPhone(phone);
    }
}