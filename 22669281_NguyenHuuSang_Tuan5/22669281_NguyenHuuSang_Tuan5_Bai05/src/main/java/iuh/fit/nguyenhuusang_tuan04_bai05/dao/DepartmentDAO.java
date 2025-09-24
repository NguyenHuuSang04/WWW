package iuh.fit.nguyenhuusang_tuan04_bai05.dao;

import iuh.fit.nguyenhuusang_tuan04_bai05.model.Department;
import iuh.fit.nguyenhuusang_tuan04_bai05.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private DBUtil dbutil;

    public DepartmentDAO(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
    }

    // Lấy tất cả phòng ban
    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (
                Connection conn = dbutil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Department d = new Department(rs.getInt("id"), rs.getString("name"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // Tìm kiếm phòng ban theo tên (LIKE)
    public List<Department> searchByName(String keyword) {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments WHERE name LIKE ?";
        try (
                Connection conn = dbutil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Department d = new Department(rs.getInt("id"), rs.getString("name"));
                    list.add(d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy phòng ban theo id
    public Department getDepartmentById(int id) {
        String sql = "SELECT * FROM departments WHERE id=?";
        try (
                Connection conn = dbutil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Department(rs.getInt("id"), rs.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm phòng ban
    public void save(Department dep) {
        String sql = "INSERT INTO departments(name) VALUES (?)";
        try (
                Connection conn = dbutil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, dep.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sửa phòng ban
    public void update(Department dep) {
        String sql = "UPDATE departments SET name=? WHERE id=?";
        try (
                Connection conn = dbutil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, dep.getName());
            ps.setInt(2, dep.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa phòng ban
    public void delete(int id) {
        String sql = "DELETE FROM departments WHERE id=?";
        try (
                Connection conn = dbutil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}