package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: Order
 * @Tạo vào ngày: 10/20/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Entity // đánh dấu lớp Order là 1 thực thể để ánh xạ tới csdl
@Table(name = "orders") // xác định tên baảng trong csdl
@Data
@AllArgsConstructor// constructor có tất cả tham số
@NoArgsConstructor// constructor không có tham số
@ToString(exclude = "orderLines") // bỏ trường orderLines khi gọi method toString của order
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// id tự động tăng lên 1
    private Integer id;

    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "customer_id")// xác định tên cột trong baảng orders để lưu khóa ngoại liên kết với customer
    private Customer customer;

    //mappedBy: trường order trong lớp OrderLine là nơi ánh xạ ngược mqh này
    //cascade : thực hiện tất cả thac tác ( persist, merge, remove, refresh, detach ) trên cha thì con sẽ tự động thay đổi theo
    // - persist: lưu cha ( order ) thì con (orderLines) cũng sẽ được lưu
    // - remove: xóa cha ( order ) thì tất cả orderLine liên kết với order đó cx bị xóa theo
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();
}