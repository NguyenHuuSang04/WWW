package edu.iuh.fit.nguyenhuusang_tuan02;

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
 * @Class: UploadMultiServlet
 * @Tạo vào ngày: 8/25/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet("/uploadmulti")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class UploadMultiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UploadMultiServlet() {
    }

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;

        File uploadDir = new File((uploadPath));
        if(!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        for(Part part : req.getParts()) {
            String fileName = getFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                part.write(uploadPath + File.separator + fileName);
            }
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h3>Files uploads successfully to " + uploadPath + "</h3>");
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return  token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return  null;
    }
}