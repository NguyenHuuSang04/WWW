package edu.iuh.fit.nguyenhuusang_tuan5_bai06.dao;

import edu.iuh.fit.nguyenhuusang_tuan5_bai06.model.DanhMuc;
import edu.iuh.fit.nguyenhuusang_tuan5_bai06.model.TinTuc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan5_Bai06
 * @Class: TinTuc
 * @Tạo vào ngày: 9/23/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class DanhSachTinTucQuanLy {
    private DataSource dataSource;

    public DanhSachTinTucQuanLy(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Lấy toàn bộ tin tức
    public List<TinTuc> getAllTinTuc() {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TinTuc t = new TinTuc(
                        rs.getString("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getString("MADM")
                );
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy tin tức theo mã danh mục
    public List<TinTuc> getTinTucByMaDM(String maDM) {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC WHERE MADM = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDM);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TinTuc t = new TinTuc(
                            rs.getString("MATT"),
                            rs.getString("TIEUDE"),
                            rs.getString("NOIDUNGTT"),
                            rs.getString("LIENKET"),
                            rs.getString("MADM")
                    );
                    list.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm tin tức mới
    public boolean addTinTuc(TinTuc tinTuc) {
        String sql = "INSERT INTO TINTUC(MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tinTuc.getMaTT());
            ps.setString(2, tinTuc.getTieuDe());
            ps.setString(3, tinTuc.getNoiDungTT());
            ps.setString(4, tinTuc.getLienKet());
            ps.setString(5, tinTuc.getMaDM());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa tin tức theo mã
    public boolean deleteTinTuc(String maTT) {
        String sql = "DELETE FROM TINTUC WHERE MATT = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maTT);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách danh mục
    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DanhMuc dm = new DanhMuc(
                        rs.getString("MADM"),
                        rs.getString("TENDANHMUC"),
                        rs.getString("NGUOIQUANLY"),
                        rs.getString("GHICHU")
                );
                list.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}