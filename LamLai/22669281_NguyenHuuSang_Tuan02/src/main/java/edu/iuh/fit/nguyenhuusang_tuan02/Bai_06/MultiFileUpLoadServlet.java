package edu.iuh.fit.nguyenhuusang_tuan02.Bai_06;

import edu.iuh.fit.nguyenhuusang_tuan02.HelloServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan02
 * @Class: UpLoadMutiFile
 * @Tạo vào ngày: 9/3/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/uploadmulti")
// báo cho servlet container rằng Servlet này có thể xử lý form với enctyple = "multipart/form-data" ( tức là upload fil )

@MultipartConfig(
        fileSizeThreshold = 1024*1024,// ~1MB: file nhỏ hơn 1 MB giữ trong RAM, lớn hơn thì ghi ra disk.
        maxFileSize = 1024*1024*10, // kích thước tối đa: mỗi file tối đa 10 MB, nếu vượt quá sẽ báo lỗi
        maxRequestSize = 1024 * 1024 * 50 // 50MB, kích thước tối đa cho toàn bộ request ( tất cả file + dữ liệu form )
)
public class MultiFileUpLoadServlet extends HttpServlet {
    private static  final long serialVersionUID = 1L;
    public MultiFileUpLoadServlet() {}
    private static final String UPLOAD_DIR = "uploads";// tên thư mục lưu file upload

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR; // tạo đường dẫn upload, trả về đường dẫn tuyệt đối tới thư mục gốc của ứng dụng web trên sever
        //(VD: D:\Tomcat\webapps\YourApp\).
        //Nối thêm UPLOAD_DIR → VD: D:\Tomcat\webapps\YourApp \ uploads.


        // kiểm tra $ tạo thư mục
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) { // nếu folder trong đường dẫn chưa ồn tại --> tạo mới
            uploadDir.mkdir(); // lệnh tạo mới folder
        }

        // lặp qua các file được upload
        for (Part part: req.getParts()) { // lấy all các part trong form ( gồm text input + file input )
            String fileName = getFileName(part); // lấy tên file từ header
            if(fileName != null && !fileName.isEmpty()) {
                part.write((uploadPath + File.separator + fileName)); // ghi file ra ổ đĩa
            }
        }

        // thông báo về client ( nếu ghi file thành công )
        resp.setContentType("text/html;charset=UTF-8");// thiết lập kiể tra về là UTF 8
        resp.getWriter().println("<h3>Files uploaded successfully to " + uploadPath + "</h3>");// in thông báo success + đường dẫn
    }

    // hàm lấy tên file
    // Part: part: là một thành phần trong request multipart/form-data ( mỗi file upload là 1 part )
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition"); // lấy giá trị header content-disposition từ Part
        String[] tokens = contentDisp.split(";");// tách chuỗi header content-disposition thành các phần nhỏ (tokens), ngăn cảnh bởi dấu ;
        for (String token : tokens) { // duyệt qua từng token trong mảng
            if(token.trim().startsWith("filename")) { // kiểm tra token nào bắt đầu bằng filename
                return  token.substring(token.indexOf("=") + 2, token.length() -1 );
            }
        }
        return  null;
    }
}