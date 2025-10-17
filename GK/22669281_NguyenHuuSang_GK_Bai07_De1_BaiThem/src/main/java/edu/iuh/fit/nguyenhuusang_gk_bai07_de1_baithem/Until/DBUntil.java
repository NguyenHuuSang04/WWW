package edu.iuh.fit.nguyenhuusang_gk_bai07_de1_baithem.Until;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De1_BaiThem
 * @Class: Until
 * @Tạo vào ngày: 9/29/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DBUntil {
    private DataSource dataSource;

    public DBUntil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection(){
        Connection connection ;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}