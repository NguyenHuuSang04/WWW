package edu.iuh.fit.nguyenhuusang_tuan04.dao;

import edu.iuh.fit.nguyenhuusang_tuan04.beans.Product;
import edu.iuh.fit.nguyenhuusang_tuan04.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04
 * @Class: ProductDAO
 * @Tạo vào ngày: 9/15/2025
 * @Tác giả: Nguyen Huu Sang
 */
// sử dụng class DBUtil thực hiện kết nối DTB và thực thi truy vấn từ DTB
// ProductDao thực hiện phương thúc trực tiếp truy vấn table Product như select, insert, update, delete
public class ProductDAO {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    // Read ALL
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = dbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String model = rs.getString("MODEL");
                Double price = rs.getDouble("PRICE");
                Integer quatity = rs.getInt("QUANTITY");
                String image = rs.getString("IMGURL");
                String description = rs.getString("DESCRIPTION");
                Product p = new Product(id, model, price, quatity, description, image);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Read By ID
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE ID=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Integer proid = rs.getInt("ID");
                    String model = rs.getString("MODEL");
                    Double price = rs.getDouble("PRICE");
                    Integer quantity = rs.getInt("QUANTITY");
                    String image = rs.getString("IMGURL");
                    String description = rs.getString("DESCRIPTION");

                    Product p = new Product(proid, model, price, quantity, description, image);
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}