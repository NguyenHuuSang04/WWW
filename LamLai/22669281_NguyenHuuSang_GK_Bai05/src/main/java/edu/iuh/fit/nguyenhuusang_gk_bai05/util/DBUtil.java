package edu.iuh.fit.nguyenhuusang_gk_bai05.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai05
 * @Class: DBUtil
 * @Tạo vào ngày: 9/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DBUtil {
    private DataSource dataSource;

    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        Connection connection;
        try{
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}