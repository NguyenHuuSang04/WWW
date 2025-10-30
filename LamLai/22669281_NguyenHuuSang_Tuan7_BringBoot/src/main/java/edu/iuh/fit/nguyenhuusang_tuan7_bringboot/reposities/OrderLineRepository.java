package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Interface: OrderLineRepository
 * @Tạo vào ngày: 10/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findByOrder_Id(Integer orderId);
}