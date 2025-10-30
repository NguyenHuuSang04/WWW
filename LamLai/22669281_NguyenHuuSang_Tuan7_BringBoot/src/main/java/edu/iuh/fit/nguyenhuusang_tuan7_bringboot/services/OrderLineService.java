package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.services;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.OrderLine;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities.OrderLineRepository;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: OrderLine
 * @Tạo vào ngày: 10/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    public List<OrderLine> findAllOrderLine() {
        return orderLineRepository.findAll();
    }

    public List<OrderLine> findOrderLineById(Integer id) {
        return orderLineRepository.findByOrder_Id(id);
    }

    public OrderLine saveOrderLine(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public void deleteOrderLine(Integer id) {
        orderLineRepository.deleteById(id);
    }


}