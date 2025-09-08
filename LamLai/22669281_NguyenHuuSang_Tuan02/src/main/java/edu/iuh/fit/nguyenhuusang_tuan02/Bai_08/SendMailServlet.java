package edu.iuh.fit.nguyenhuusang_tuan02.Bai_08;

import jakarta.activation.DataHandler;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.util.Properties;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan02
 * @Class: SendMailServlet
 * @Tạo vào ngày: 9/5/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/formSendMail")
@MultipartConfig
public class SendMailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String USERNAME = "sangnguyenn29@gmail.com";
    private final String PASSWORD = "hjaw hbaa boso huby";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
//        super.doPost(req, resp);
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        Part filePart = request.getPart("file"); // lấy file upload

        try {
            //1. cấu hình SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // chỉ định máy chủ SMTP mà chương trình sẽ dùng để gửi mail
            props.put("mail.smtp.port", "587");// chỉ định cổng (port) smtp để gửi mail
            props.put("mail.smtp.auth", "true"); // báo cho JavaMail rằng cần xác thực đăng nhập ( dùng Usename, pass)
            props.put("mail.smtp.starttls.enable", "true"); // bật STARTTLS để mã hóa đường truyền giữa client  và server, bắt buộc khi gửi mail qua google ( nếu không, gg sẽ từ chối )

            // 2. tạo Session có xác thực
            // đăng nhập ào Gmail SMTP server
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

            // 3. Tạo message
            MimeMessage message = new MimeMessage(session); // tạo đối tượng email gắn với session vừa tọa
            message.setFrom(new InternetAddress(USERNAME)); // gắn người gửi ( setFrom )
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));// gắn người nhận ( to ) từ form gửi lên
            message.setSubject(subject, "UTF-8"); // gắn tiêu đề mail ( subject ) có mã hóa UTF-8 (tiếng Việt)

            // 4. Nội dung chính ( content )
            MimeBodyPart textPart = new MimeBodyPart(); // tạo phần thân (body) của email
            textPart.setText(content, "UTF-8"); // gán nội dung văn bản từ form gửi lên, có mã hóa UTF-8

            // 5. File đính kèm ( nếu có )
            Multipart multipart = new MimeMultipart();// Email có thể gồm nhiều phần ( văn bản, hình ảnh, file đính kèm...)
            multipart.addBodyPart(textPart); // thêm phần văn bản ( nội dung chính ) vào multipart

            if(filePart != null && filePart.getSize() > 0) { // kiểm tra nếu có file upload
                MimeBodyPart attachPart = new MimeBodyPart(); // tạo 1 phần mới để đính kèm file
                String fileName = filePart.getSubmittedFileName(); // lấy tên file
                InputStream fileContent = filePart.getInputStream(); // lấy nội dung file dưới dạng InputStream (dữ liệu nhị phân)
                attachPart.setFileName(fileName); // gán tên file để hiển thị khi nhận mail

                // gán dữ liệu file (dữ liệu nhị phân) và kiểu file (MIME Type)
                // dùng ByteArrayDataSource để chuyển đổi InputStream sang dữ liệu nhị phân + kiểu MIME
                attachPart.setDataHandler(new DataHandler(new ByteArrayDataSource(fileContent, getServletContext().getMimeType(fileName))));
                multipart.addBodyPart(attachPart); // thêm phần đính kèm vào multipart ( email )
            }

            // 6. Gắn multipart vào message
            message.setContent(multipart); // gắn toàn bộ nội dung ( text + file ) vào email

            // 7. gửi mail
            Transport.send(message); // gửi email đi qua SMTP server Gmail
            respone.setContentType("text/html; charset=UTF-8");
            respone.getWriter().println("<h3>Gửi mail thành công! </h3>");
        } catch (Exception e) {
            throw new ServletException("Lỗi gửi mail: " + e.getMessage(), e);
        }
    }

    // tóm lại các bước gửi mail
    // 1. cấu hình SMTP (host, port, auth, TLS/SSL)
    // 2. tạo Session có xác thực ( đăng nhập vào SMTP server)
    // 3. tạo Message (người gửi, người nhận, tiêu đề)
    // 4. tạo phần nội dung chính (text, html)
    // 5. tạo phần file đính kèm (nếu có)
    // 6. gắn tất cả vào Message
    // 7. gửi mail (Transport.send)

}