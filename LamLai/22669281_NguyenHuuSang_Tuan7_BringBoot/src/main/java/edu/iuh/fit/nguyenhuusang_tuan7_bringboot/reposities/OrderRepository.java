package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.Order;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.OrderLine;
import org.hibernate.Internal;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Interface: OrderRepository
 * @Tạo vào ngày: 10/22/2025
 * @Tác giả: Nguyen Huu Sang
 */

// định nghĩa các thao tác với csdl
// sử dụng spring data JPA or thư viện tương tự để tự động hoa việc thao tác với dữ liệu
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // tìm hóa đơn theo khách hàng
    List<Order> findByCustomer_Id(Integer customerId);

    List<Order> findByDate(LocalDate date);



}