package edu.iuh.fit.nguyenhuusang_tuan7.controller;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Customer;
import edu.iuh.fit.nguyenhuusang_tuan7.entities.Order;
import edu.iuh.fit.nguyenhuusang_tuan7.services.CustomerService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: OrderController
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private CustomerService customerService;

    // Hiển thị danh sách hóa đơn
    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    // Xem chi tiết hóa đơn (và các OrderLine)
    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Integer id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        // order.getOrderLines() để hiển thị chi tiết hóa đơn theo mối quan hệ 1-n
        return "order/order-detail";
    }

//    // Thêm hóa đơn
//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("order", new Order());
//        return "order/order-form";
//    }
//
//    @PostMapping("/add")
//    public String addOrder(@ModelAttribute Order order) {
//        orderService.saveOrder(order);
//        return "redirect:order/orders";
//    }

    // Sửa hóa đơn
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Integer id, Model model) {
//        Order order = orderService.getOrderById(id);
//        List<Customer> customers = customerService.getAllCustomers(); // Lấy danh sách khách hàng
//        model.addAttribute("order", order);
//        model.addAttribute("customers", customers);
//        return "order/order-form";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editOrder(@PathVariable Integer id, @ModelAttribute Order order) {
//        order.setId(id);
//        orderService.saveOrder(order);
//        return "redirect:order/orders";
//    }

    // Xóa hóa đơn
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    // Tìm kiếm hóa đơn theo mã khách hàng
    @GetMapping("/search/customer")
    public String searchByCustomer(@RequestParam Integer customerId, Model model) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        model.addAttribute("orders", orders);
        model.addAttribute("customerId", customerId);
        return "order/orders";
    }

    // Tìm kiếm hóa đơn theo ngày
    @GetMapping("/search/date")
    public String searchByDate(@RequestParam String dateStr, Model model) {
        // Chuyển dateStr về Calendar nếu cần
        Calendar date = Calendar.getInstance();
        // ... chuyển đổi dateStr sang Calendar ...
        List<Order> orders = orderService.getOrdersByDate(date);
        model.addAttribute("orders", orders);
        model.addAttribute("date", dateStr);
        return "order/orders";
    }
}