package edu.iuh.fit.nguyenhuusang_gk_bai05.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai05.model.Department;
import edu.iuh.fit.nguyenhuusang_gk_bai05.model.Employee;
import edu.iuh.fit.nguyenhuusang_gk_bai05.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai05
 * @Class: DepartmentDAO
 * @Tạo vào ngày: 9/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
// tìm theo tên, thêm,sửa, xóa, thêm emp vào
public class DepartmentDAO {
    private DBUtil dbUtil; // khai báo private

    public DepartmentDAO(DataSource dataSource) { // -	Contructor dao(DataSource dataSouce)

        dbUtil = new DBUtil(dataSource);
    }

    public DepartmentDAO() {
    }


    public Department getDepartmentByID(String departmentID) {
        String sql = "SELECT * FROM departments WHERE id=?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, departmentID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String name = resultSet.getString("NAME");

                    Department department = new Department(id, name);
                    return department;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public List<Department> getAllDepartment() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Connection connection = dbUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");

                Department department = new Department(id, name);

                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departments;
    }

    //Thêm
    public void saveDepartment(Department department) {
        String sql = "INSERT INTO departments(name) VALUES(?)";
        try(Connection connection = dbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, department.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateDepartment(Department department) {
        String sql = "UPDATE departments SET name=? WHERE id=?";
        try (Connection connection = dbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Remove
    public void removeDepartment(String departmentId) {
        String sql = "DELETE FROM departments WHERE id=?";
        try(Connection connection = dbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, departmentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Department> searchDepartmentByName(String keyword) {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments WHERE name LIKE ?";

        try(Connection connection = dbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, "%" + keyword +"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String name = resultSet.getString("NAME");

                Department department = new Department(id, name);

                departments.add(department);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return departments;

    }
}