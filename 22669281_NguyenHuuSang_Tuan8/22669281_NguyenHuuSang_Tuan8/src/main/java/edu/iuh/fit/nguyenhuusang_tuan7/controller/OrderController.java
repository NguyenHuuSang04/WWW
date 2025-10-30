package edu.iuh.fit.nguyenhuusang_tuan7.controller;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Customer;
import edu.iuh.fit.nguyenhuusang_tuan7.entities.Order;
import edu.iuh.fit.nguyenhuusang_tuan7.entities.Orderline;
import edu.iuh.fit.nguyenhuusang_tuan7.entities.Product;
import edu.iuh.fit.nguyenhuusang_tuan7.services.CustomerService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.OrderLineService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.OrderService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String findAllOrder(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @GetMapping("/search")
    public String findOrderByDate(@RequestParam LocalDate date, Model model) {
        List<Order> orders = orderService.getOrdersByDate(date);
        model.addAttribute("keyword", date);
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @GetMapping("/detail/{id}")
    public String viewDetailOrder(@PathVariable Integer id, Model model) {
        List<Orderline> orderLines = orderLineService.findOrderLineById(id);
//        System.out.println("orderLines: " + orderLines);
        model.addAttribute("orderLines",orderLines);
        return "order/order-detail";
    }

    // Thêm hóa đơn
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        System.out.println(customerService.getAllCustomers());
        List<Product> products = productService.getAllProducts();

        if (products == null || products.isEmpty()) {
            throw new IllegalStateException("No products found!");
        }

        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("products", products);
        return "order/order-form";
    }



    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order) {
        // Duyệt qua tất cả OrderLine để tính giá mua và gán hóa đơn
        for (Orderline line : order.getOrderLines()) {
            Product product = productService.getProductById(line.getProduct().getId());
            line.setProduct(product); // Gán thông tin sản phẩm
            line.setPurchasePrice(product.getPrice().multiply(BigDecimal.valueOf(line.getAmount()))); // Tính giá mua
            line.setOrder(order); // Gán hóa đơn vào từng dòng sản phẩm
        }

        // Lưu hóa đơn và các OrderLine liên kết
        order.setOrderLines(order.getOrderLines());
        orderService.saveOrder(order);

        return "redirect:/orders";
    }
}