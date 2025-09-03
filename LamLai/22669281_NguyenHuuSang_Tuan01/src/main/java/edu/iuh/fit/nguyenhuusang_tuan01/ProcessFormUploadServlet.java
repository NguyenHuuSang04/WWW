package edu.iuh.fit.nguyenhuusang_tuan01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan01
 * @Class: ProcessFormUploadServlet
 * @Tạo vào ngày: 8/24/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/processFormUpload")// khai báo servlet với URL mapping
@MultipartConfig // khai báo servlet này sẽ xử lý form có file upload ( ở đây là picture )
public class ProcessFormUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException, ServletException {
        // request: chứa dữ liệu client gửi lên ( input form, file upload, ... )
        // response: chứa dữ liệu server trả về cho client ( HTML, JSON, ... )

        response.setContentType("text/html;charset=UTF-8"); // thiết lập kiểu dữ liệu trả về là HTML và mã hóa UTF-8 để hiển thị tiếng Việt
        PrintWriter out = response.getWriter(); // lấy đối tượng PrintWriter để ghi dữ liệu trả về cho client

        try {
            //lấy dữ liệu từ request
            String firstName = request.getParameter("firstName"); // getParameter: lấy giá trị của input form: text, radio, select, textarea
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String facebook = request.getParameter("facebook");
            String gender = request.getParameter("gender");
            String[] hobbies = request.getParameterValues("hobbies");// getParameterValues: lấy giá trị của input form: checkbox ( có thể có nhiều giá trị )
            String bio = request.getParameter("bio");

            // xử lý file upload
            Part filePart = request.getPart("profilePicture"); // getPart: lấy file upload từ form
            String fileName = filePart.getSubmittedFileName(); // lấy tên file upload

            // Hiển thị thông tin
            out.println("<html><body>");
            out.println("<h2>Registration Details: </h2>");
            out.println("<p>Full name: " + firstName + " " +lastName + "</p>");
            out.println("<p>Username: " + firstName + "</p>");
            out.println("<p>Email: " + email + "</p>");
            out.println("<p>Password: " + password + "</p>");
            out.println("<p>Facebook: <a href='" + facebook + "'>" + facebook + "</a></p>");
            out.println("<p>Gender:" + gender +"</p>");

            if(hobbies!=null){
                 out.println("<p>Hobbies: ");
                 for(String hobby: hobbies) {
                     out.print(hobby + " ");
                 }
                 out.println("</p>");
            }

            out.println("<p>Profile Picture: " + fileName  + "</p>");
            out.println("<p>Bio: " + bio + "</p>");
            out.println("</body></html  >");
        } finally {
            out.close();
        }
    }
}