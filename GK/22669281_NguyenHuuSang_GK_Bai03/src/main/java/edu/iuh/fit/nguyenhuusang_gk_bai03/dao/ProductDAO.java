package edu.iuh.fit.nguyenhuusang_gk_bai03.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai03.beans.Product;
import edu.iuh.fit.nguyenhuusang_gk_bai03.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai03
 * @Class: ProductDAO
 * @Tạo vào ngày: 9/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class ProductDAO {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    // Real all
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = dbUtil.getConnection(); // tạo connect tới database
             Statement stmt = conn.createStatement(); // tạo 1 đối tượng statement ( câu lệnh SQL )
             ResultSet rs = stmt.executeQuery(sql)) { // thực thi câu lệnh SQL --> trả về kết quả ResultSet

            while (rs.next()) {
                Integer id = rs.getInt("ID"); // kiểu dữ liệu là int id được không?
                String model = rs.getString("MODEL");
                Double price = rs.getDouble("PRICE");
                Integer quality = rs.getInt("QUANTITY");
                String image = rs.getString("IMGURL");
                String description = rs.getString("DESCRIPTION");
                Product p = new Product(id, model, description, quality, price, image);
                list.add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

        // Dùng PreparedStatement khi câu lệnh SQL có chứa tham số
        // Dùng Statement khi câu lệnh cố định, không có tham số
    }

    // read by ID
    public Product getProductById(int ProductID) {
        String sql = "SELECT * FROM products WHERE ID =?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {// tạo 1 đối tượng connect tới conn, sử dụng câu sql có chứa tham số ?
            ps.setInt(1, ProductID); // gắn giá trị cho tham số ? thứ nhất là productID

            try (ResultSet rs = ps.executeQuery()) { // thực thi câu lệnh --> trả về kq ResultSet
                if (rs.next()) {
                    Integer id = rs.getInt("ID");
                    String model = rs.getString("MODEL");
                    Double price = rs.getDouble("PRICE");
                    Integer quality = rs.getInt("QUANTITY");
                    String image = rs.getString("IMGURL");
                    String description = rs.getString("DESCRIPTION");
                    Product p = new Product(id, model, description, quality, price, image);
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}