package edu.iuh.fit.nguyenhuusang_tuan04_bai04.dao;

import edu.iuh.fit.nguyenhuusang_tuan04_bai04.beans.Book;
import edu.iuh.fit.nguyenhuusang_tuan04_bai04.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04_Bai04
 * @Class: BookDAO
 * @Tạo vào ngày: 9/15/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class BookDAO {
    private DBUtil dbUtil;

    public BookDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    /**
     * Lấy tất cả sách từ database
     */
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = dbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("imgurl");
                String description = rs.getString("description");
                Book b = new Book(id, title, author, price, quantity, image, description);
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book getBookById(String id) {
        String sql = "SELECT * FROM books WHERE id=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("imgurl");
                    String description = rs.getString("description");
                    return new Book(id, title, author, price, quantity, image, description);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}