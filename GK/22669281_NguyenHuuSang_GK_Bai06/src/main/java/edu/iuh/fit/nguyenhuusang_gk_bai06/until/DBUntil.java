package edu.iuh.fit.nguyenhuusang_gk_bai06.until;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai06
 * @Class: DBUntil
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DBUntil {
    private DataSource dataSource; // tạo dataSource

    public DBUntil(DataSource dataSource) { // contructor cho datasource
        this.dataSource = dataSource;
    }

    public Connection getConnection() { // viết connection
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}