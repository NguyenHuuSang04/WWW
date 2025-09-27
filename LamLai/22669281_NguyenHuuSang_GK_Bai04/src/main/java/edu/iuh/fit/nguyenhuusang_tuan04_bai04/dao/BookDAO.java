package edu.iuh.fit.nguyenhuusang_tuan04_bai04.dao;

import edu.iuh.fit.nguyenhuusang_tuan04_bai04.model.Book;
import edu.iuh.fit.nguyenhuusang_tuan04_bai04.util.DBUtil;

import javax.sql.DataSource;
import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan04_Bai04
 * @Class: BookDAO
 * @Tạo vào ngày: 9/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class BookDAO {
    private DBUtil dbUtil;

    public BookDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    // Read all
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = dbUtil.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("ID");
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                Double price = rs.getDouble("PRICE");
                Integer quantity = rs.getInt("QUANTITY");
                String imgURL = rs.getString("IMGURL");
                String description = rs.getString("DESCRIPTION");

                Book b = new Book(id, title, author, quantity, price, imgURL, description);
                list.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // read by id
    public Book getBookById(String id) {
        String sql = "SELECT * FROM books WHERE id=?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String title = resultSet.getString("TITLE");
                    String author = resultSet.getString("AUTHOR");
                    Double price = resultSet.getDouble("PRICE");
                    Integer quantity = resultSet.getInt("QUANTITY");
                    String imgURL = resultSet.getString("IMGURL");
                    String description = resultSet.getString("DESCRIPTION");

                    Book b = new Book(id, title, author, quantity, price, imgURL, description);
                    return b;
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}