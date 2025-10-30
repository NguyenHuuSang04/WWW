package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.services;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.Order;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: OrderService
 * @Tạo vào ngày: 10/22/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service // đánh dấu đây là 1 class service
public class OrderService {
    @Autowired // tiêm các dependency vào lớp mà không cần khởi tạo thủ công
    private OrderRepository orderRepository;

    //CRUD
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    // add, sửa
    public Order saveOrder (Order order) {
       return orderRepository.save(order);

    }

    // xóa
    public void deleteOrder (Integer id) {
        orderRepository.deleteById(id);
    }

    // tìm hóa đơn theo customer id
    public List<Order> getOrdersByCustomerId(Integer id) {
        return  orderRepository.findByCustomer_Id(id);
    }

    // tìm hóa đơn theo ngày
    public List<Order> getOrdersByDate(LocalDate date) {
        return orderRepository.findByDate(date);
    }

}