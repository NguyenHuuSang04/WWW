package edu.iuh.fit.nguyenhuusang_gk_bai06.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai06.model.DanhMuc;
import edu.iuh.fit.nguyenhuusang_gk_bai06.until.DBUntil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai06
 * @Class: DanhMucDAO
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DanhMucDAO {
    private DBUntil dbUntil;

    public DanhMucDAO(DataSource dataSource) {
        dbUntil = new DBUntil(dataSource);
    }

    public DanhMucDAO() {
    }

    public DanhMuc getDanhMucById(String idDM) {
        String sql = "SELECT * FROM danhmuc WHERE MADM=?";
        try (Connection connection = dbUntil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, idDM);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("MADM");
                String tenDM = resultSet.getString("TENDANHMUC");
                String nguoiQL = resultSet.getString("NGUOIQUANLY");
                String ghiChu = resultSet.getString("GHICHU");

                DanhMuc danhMuc = new DanhMuc(id, tenDM, nguoiQL, ghiChu);
                return danhMuc;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> danhMucs = new ArrayList<>();
        String sql = "SELECT * FROM danhmuc";
        try (Connection connection = dbUntil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("MADM");
                String tenDM = resultSet.getString("TENDANHMUC");
                String nguoiQL = resultSet.getString("NGUOIQUANLY");
                String ghiChu = resultSet.getString("GHICHU");

                DanhMuc danhMuc = new DanhMuc(id, tenDM, nguoiQL, ghiChu);
                danhMucs.add(danhMuc);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return danhMucs;

    }
}