package edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model.Score;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.model.Student;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de3_baithem.until.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
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

        try (Connection connection = dbUtil.getConnection();
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


                Score score = new Score(scoreID, student, subjectName, scorePoint, semester, examDate, scoreType, notes);
                scores.add(score);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scores;
    }

    public void updateScore(Score score, String idStu) {
        //valid điểm
        if (score.getScore() < 0 || score.getScore() > 10) {
            throw new IllegalArgumentException("ScorePoint 0 - 10");
        }

        // valid sinh viên tồn tại
        Student student = studentDAO.getStudentByID(idStu);
        if (student == null) {
            throw new IllegalArgumentException("Student not tồn tại");
        }

        String sql = "UPDATE scores SET subject_name = ?, score = ?, semester = ?, exam_date = ?, score_type = ?, notes = ?  WHERE student_id = ? AND score_id = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, score.getSubjectName());
            preparedStatement.setDouble(2, score.getScore());
            preparedStatement.setString(3, score.getSemester());
            preparedStatement.setDate(4, Date.valueOf(score.getExemDate()));
            preparedStatement.setString(5, score.getScoreType());
            preparedStatement.setString(6, score.getNotes());
            preparedStatement.setString(7, idStu);
            preparedStatement.setString(8, score.getScoreId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveScore(Score score, String idStu) {
        //valid điểm
        if (score.getScore() < 0 || score.getScore() > 10) {
            throw new IllegalArgumentException("ScorePoint 0 - 10");
        }

        // valid sinh viên tồn tại
        Student student = studentDAO.getStudentByID(idStu);
        if (student == null) {
            throw new IllegalArgumentException("Student not tồn tại");
        }
        if(score.getScoreId()==null || score.getScoreId().isEmpty()) {
            score.setScoreId(java.util.UUID.randomUUID().toString());
        }


        String sql = "INSERT INTO scores (student_id, subject_name, score, semester, exam_date, score_type, notes) VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, idStu);
            preparedStatement.setString(2, score.getSubjectName());
            preparedStatement.setDouble(3, score.getScore());
            preparedStatement.setString(4, score.getSemester());
            preparedStatement.setDate(5, Date.valueOf(score.getExemDate()));
            preparedStatement.setString(6, score.getScoreType());
            preparedStatement.setString(7, score.getNotes());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Score getScoreByIdScore(String scoreId) {
        String sql = "SELECT * FROM scores WHERE score_id = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, scoreId);
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


                Score score = new Score(scoreID, student, subjectName, scorePoint, semester, examDate, scoreType, notes);
                return score;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}