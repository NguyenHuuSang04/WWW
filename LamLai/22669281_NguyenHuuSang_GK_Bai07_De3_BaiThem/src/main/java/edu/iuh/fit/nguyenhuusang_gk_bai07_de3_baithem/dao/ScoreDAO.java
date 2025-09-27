package edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model.Score;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model.Student;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.until.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De3_BaiThem
 * @Class: ScoreDAO
 * @Tạo vào ngày: 9/26/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class ScoreDAO {
    private DBUtil dbUtil;
    private StudentDAO studentDAO;

    public ScoreDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
        studentDAO = new StudentDAO(dataSource);
    }

    public List<Score> getScoreByIdStudent(String studentID) {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM scores WHERE student_id = ?";

        try(Connection connection = dbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String scoreID = resultSet.getString("score_id");

                String student_id = resultSet.getString("student_id");
                Student student = studentDAO.getStudentByID(student_id);

                String subjectName = resultSet.getString("subject_name");
                Double scorePoint = resultSet.getDouble("score");
                String semester = resultSet.getString("semester");
                LocalDate examDate = resultSet.getDate("exam_date").toLocalDate();
                String scoreType = resultSet.getString("score_type");
                String notes = resultSet.getString("notes");


                Score score = new Score(scoreID,student, subjectName, scorePoint, semester, examDate, scoreType, notes);
                scores.add(score);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scores;
    }
}