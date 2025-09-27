package edu.iuh.fit.nguyenhuusang_gk_bai06.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai06.model.DanhMuc;
import edu.iuh.fit.nguyenhuusang_gk_bai06.model.TinTuc;
import edu.iuh.fit.nguyenhuusang_gk_bai06.until.DBUntil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai06
 * @Class: DanhSachTinTucQuanLy
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DanhSachTinTucQuanLyDAO {
    private DBUntil dbUntil;
    private DanhMucDAO danhMucDAO;

    public DanhSachTinTucQuanLyDAO(DataSource dataSource) {
        dbUntil = new DBUntil(dataSource);
        danhMucDAO = new DanhMucDAO(dataSource);
    }

    public DanhSachTinTucQuanLyDAO() {
    }

    public List<TinTuc> getAllTinTuc() {
        List<TinTuc> tinTucs = new ArrayList<>();
        String sql = "SELECT * FROM tintuc";
        try (Connection connection = dbUntil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id = resultSet.getString("MATT");
                String tieuDe = resultSet.getString("TIEUDE");
                String noiDUng = resultSet.getString("NOIDUNGTT");
                String lienKet = resultSet.getString("LIENKET");
                String maDM = resultSet.getString("MADM");


                DanhMuc danhMuc = danhMucDAO.getDanhMucById(maDM);
                TinTuc tinTuc = new TinTuc(id, tieuDe, noiDUng, lienKet, danhMuc);

                tinTucs.add(tinTuc);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tinTucs;
    }

    public List<TinTuc> getTinTucByDanhMuc(String idDanhMuc) {
        List<TinTuc> tinTucs = new ArrayList<>();
        String sql = "SELECT * FROM tintuc WHERE MADM = ?";

        try (Connection connection = dbUntil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, idDanhMuc);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("MATT");
                String tieuDe = resultSet.getString("TIEUDE");
                String noiDUng = resultSet.getString("NOIDUNGTT");
                String lienKet = resultSet.getString("LIENKET");
                String maDM = resultSet.getString("MADM");


                DanhMuc danhMuc = danhMucDAO.getDanhMucById(maDM);
                TinTuc tinTuc = new TinTuc(id, tieuDe, noiDUng, lienKet, danhMuc);

                tinTucs.add(tinTuc);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tinTucs;
    }

    public void saveTinTuc(TinTuc tinTuc) {
        if (tinTuc.getMaTT() == null || tinTuc.getMaTT().isEmpty()) {
            tinTuc.setMaTT(java.util.UUID.randomUUID().toString());
        }
        String sql = "INSERT INTO tintuc(MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES(?,?,?,?,?)";
        try (Connection connection = dbUntil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, tinTuc.getMaTT());
            preparedStatement.setString(2, tinTuc.getTieuDe());
            preparedStatement.setString(3, tinTuc.getNoiDungTT());
            preparedStatement.setString(4, tinTuc.getLienKet());
            preparedStatement.setString(5, tinTuc.getDanhMuc().getMaDM());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeTinTuc(String maTT) {
        String sql = "DELETE FROM tintuc WHERE MATT = ?";
        try (Connection connection = dbUntil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, maTT);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TinTuc getTinTucById(String tinTucId) {
        String sql = "SELECT * FROM tintuc WHERE MATT = ?";
        try (Connection connection = dbUntil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tinTucId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("MATT");
                String tieuDe = resultSet.getString("TIEUDE");
                String noiDUng = resultSet.getString("NOIDUNGTT");
                String lienKet = resultSet.getString("LIENKET");
                String maDM = resultSet.getString("MADM");


                DanhMuc danhMuc = danhMucDAO.getDanhMucById(maDM);
                TinTuc tinTuc = new TinTuc(id, tieuDe, noiDUng, lienKet, danhMuc);
                return tinTuc;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateTinTuc(TinTuc tinTuc) {
        String sql = "UPDATE  tintuc " +
                "SET TIEUDE = ?, NOIDUNGTT = ?, LIENKET = ?, MADM = ? " +
                "WHERE MATT = ?";

        try (Connection connection = dbUntil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tinTuc.getTieuDe());
            preparedStatement.setString(2, tinTuc.getNoiDungTT());
            preparedStatement.setString(3, tinTuc.getLienKet());
            preparedStatement.setString(4, tinTuc.getDanhMuc().getMaDM());
            preparedStatement.setString(5, tinTuc.getMaTT());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}