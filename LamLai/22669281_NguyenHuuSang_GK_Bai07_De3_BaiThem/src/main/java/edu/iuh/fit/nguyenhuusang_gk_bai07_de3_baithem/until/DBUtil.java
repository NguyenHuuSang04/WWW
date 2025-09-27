package edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.until;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De3_BaiThem
 * @Class: DBUtil
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DBUtil {
    private DataSource dataSource; // dataSource

    public DBUtil(DataSource dataSource) { // contructor
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}