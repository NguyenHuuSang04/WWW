package edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model.Student;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.until.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De3_BaiThem
 * @Class: StudentDAO
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class StudentDAO {
    private DBUtil dbUtil;

    public StudentDAO(DataSource dataSource) { // contrustor dataSource
        dbUtil = new DBUtil(dataSource);
    }

    //R
    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection connection = dbUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String className = resultSet.getString("class_name");
                String major = resultSet.getString("major");
                Integer enrollmentYear = resultSet.getInt("enrollment_year");
                String status = resultSet.getString("status");
                LocalDate createDate = resultSet.getDate("create_Date").toLocalDate();

                Student student = new Student(id, fullName, email, phone, className, major, enrollmentYear, status, createDate);
                students.add(student);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    // R tìm theo mã sv
    public Student getStudentByID(String id) {
        String sql = "SELECT * FROM students WHERE students = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String stId = resultSet.getString("student_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String className = resultSet.getString("class_name");
                String major = resultSet.getString("major");
                Integer enrollmentYear = resultSet.getInt("enrollment_year");
                String status = resultSet.getString("status");
                LocalDate createDate = resultSet.getDate("create_Date").toLocalDate();

                Student student = new Student(stId, fullName, email, phone, className, major, enrollmentYear, status, createDate);
                return student;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // R theo teen
    public List<Student> getStudentByName(String name) {
        String sql = "SELECT * FROM students WHERE full_name LIKE ?";
        List<Student> students = new ArrayList<>();

        try(Connection connection = dbUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String className = resultSet.getString("class_name");
                String major = resultSet.getString("major");
                Integer enrollmentYear = resultSet.getInt("enrollment_year");
                String status = resultSet.getString("status");
                LocalDate createDate = resultSet.getDate("create_Date").toLocalDate();

                Student student = new Student(id, fullName, email, phone, className, major, enrollmentYear, status, createDate);
                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  students;
    }

    // R theo ngành học
    public List<Student> getStudentByMajor(String majorInput) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE major LIKE ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, "%" + majorInput + "%");
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String className = resultSet.getString("class_name");
                String major = resultSet.getString("major");
                Integer enrollmentYear = resultSet.getInt("enrollment_year");
                String status = resultSet.getString("status");
                LocalDate createDate = resultSet.getDate("create_Date").toLocalDate();

                Student student = new Student(id, fullName, email, phone, className, major, enrollmentYear, status, createDate);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    //R điểm của student

    // C
    public void saveStudent(Student student) {
        // Sinh student_id
        if(student.getStudentID() == null || student.getStudentID().isEmpty()) {
            int year = student.getEnrollment_year();
            String prefix = String.valueOf(year);

            // lấy số thứ tự lớn nhất của năm đó
            String sqlMax = "SELECT MAX(student_id) FROM students WHERE student_id LIKE ?";
            String maxID = null;
            try(Connection connection = dbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlMax)) {
                preparedStatement.setString(1, prefix + "%");
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    maxID = resultSet.getString(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            int nextNum = 1;
            if(maxID != null) {
                // lấy 4 số cuối rồi +1
                String numStr = maxID.substring(prefix.length());
                nextNum = Integer.parseInt(numStr) +1 ;
            }
            String newId = String.format("%s%04d", prefix, nextNum);
            student.setStudentID(newId);

        }

        // valid năm nhập học
        if(student.getEnrollment_year() < 2020) {
            throw new IllegalArgumentException("Enrollment Year >= 2020");
        }

        //valid email tồn tại
        String sqlEmailCheck = "SELECT * FROM students WHERE email = ?";
        try (Connection connection = dbUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEmailCheck)){
            preparedStatement.setString(1, student.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next() && resultSet.getInt(1) > 0) { // getInt() để lấy giá trị ở cột đầu tiên của dòng đó
                throw  new IllegalArgumentException("Email đã tồn tại");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO students (student_id, full_name, email, phone, class_name, major, enrollment_year, status, created_date ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, student.getStudentID());
            preparedStatement.setString(2, student.getFullName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getClassName());
            preparedStatement.setString(6, student.getMajor());
            preparedStatement.setInt(7, student.getEnrollment_year());
            preparedStatement.setString(8, student.getStatus());
            preparedStatement.setDate(9, Date.valueOf(student.getCreateDate()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}