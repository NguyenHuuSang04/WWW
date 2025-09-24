package edu.iuh.fit.nguyenhuusang_tuan5_bai06.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan5_Bai06
 * @Class: DBUtil
 * @Tạo vào ngày: 9/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DBUtil {
    private DataSource dataSource;

    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}