package edu.iuh.fit.nguyenhuusang_tuan02.Bai_07;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.mariadb.jdbc.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan02
 * @Class: LoadDataToDatabaseServlet
 * @Tạo vào ngày: 9/4/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/uploadtoDadabase")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class LoadDataToDatabaseServlet extends HttpServlet {
    // khai báo thông tin kết nối database
    private String dbURL = "jdbc:mariadb://localhost:3306/uploadfileservletdb";
    private String dbUser = "root";
    private String dbPass = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        InputStream inputStream = null;
        Part filePart = req.getPart("photo"); // lấy file upload từ input có name="photo"
        if (filePart != null ){ // nếu file tồn tại,
            inputStream = filePart.getInputStream(); //mở input stream để đọc dữ liệu nhị phân (binary data)
        }

        Connection conn = null; // biến kết nối DB
        String message = null; // biến thông báo kết quả

        try {

            //Nạp driver JDBC của MariaDB
            Class.forName("org.mariadb.jdbc.Driver");


            // Tạo kết nối tới database
            conn = (Connection) DriverManager.getConnection(dbURL, dbUser, dbPass);

            // câu lệnh SQL chèn dữ liệu
            String sql = "INSERT INTO contacts (first_name, last_name, photo) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);// dùng PreparedStatement để chèn dữ liệu

            // gán giá trị cho các tham số (?)
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            if(inputStream !=null ) {
                // Nếu có file upload --> gắn dữ liệu binary vào cột photo
                statement.setBinaryStream(3, inputStream, (int) filePart.getSize());
            } else {
                statement.setNull(3, Types.BLOB); // nếu không có --> gán null
            }

            int row = statement.executeUpdate();// chạy câu lệnh chèn dữ liệu
            if (row > 0) {
                message = "Upload and save into database successful!";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            message = "ERROR: " + ex.getMessage();
        } finally { // đảm bảo kết nối DB được đóng sua khi xong
            if(conn !=null) try {
                conn.close();
            } catch (SQLException ignore) {}
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h3>" + message + "</h3>");
    }
}