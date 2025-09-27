package edu.iuh.fit.nguyenhuusang_gk_bai03.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai03
 * @Class: DBUtil
 * @Tạo vào ngày: 9/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DBUtil {
    private DataSource dataSource;// biến lưu đối tượng nguồn dữ liệu --> tạo kết nối csdl

    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = dataSource.getConnection();// lấy 1 kết nối mới từ dataSource
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}