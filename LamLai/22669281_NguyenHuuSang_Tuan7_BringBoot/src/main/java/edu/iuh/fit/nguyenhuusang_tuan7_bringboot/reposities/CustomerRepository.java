package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Interface: CustomerRepository
 * @Tạo vào ngày: 10/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findCustomerByNameContainingIgnoreCase(String name);

    Customer findCustomerById(Integer id);

    Customer findCustomerByPhone(String phone);
}