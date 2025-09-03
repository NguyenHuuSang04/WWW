package edu.iuh.fit.nguyenhuusang_tuan02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan02
 * @Class: UploadFiletoDBServlet
 * @Tạo vào ngày: 8/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/uploadToDB")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // max 10MB
public class UploadFiletoDBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Kết nối MariaDB
    private final String dbURL = "jdbc:mariadb://localhost:3306/UploadFileServletDB"; // đổi thành DB của bạn
    private final String dbUser = "root";   // user MariaDB
    private final String dbPass = "root"; // password MariaDB

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Part filePart = req.getPart("photo");

        InputStream inputStream = null;
        if (filePart != null && filePart.getSize() > 0) {
            inputStream = filePart.getInputStream();
        }

        String message = "";

        try {
            // Nạp driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Kết nối CSDL
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            String sql = "INSERT INTO contacts (first_name, last_name, photo) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            if (inputStream != null) {
                statement.setBlob(3, inputStream);
            } else {
                statement.setNull(3, Types.BLOB);
            }

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Upload and save into database successful!";
            }

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            message = "ERROR: " + ex.getMessage();
        }

        // Trả kết quả về trình duyệt
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h3>" + message + "</h3>");
    }
}