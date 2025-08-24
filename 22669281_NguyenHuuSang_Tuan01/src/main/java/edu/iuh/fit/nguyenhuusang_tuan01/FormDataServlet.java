package edu.iuh.fit.nguyenhuusang_tuan01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan01
 * @Class: FormDataServlet
 * @Tạo vào ngày: 8/18/2025
 * @Tác giả: Nguyen Huu Sang
 */
@WebServlet(name = "processFormUpload", value = "/processFormUpload")
public class FormDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String facebook = request.getParameter("facebook");
        String gender = request.getParameter("gender");
        String[] interests = request.getParameterValues("interest");
        String bio = request.getParameter("bio");

        response.getWriter().println("<h2>Form Data Received:</h2>");
        response.getWriter().println("Name: " + firstName + " " + lastName + "<br>");
        response.getWriter().println("Username: " + username + "<br>");
        response.getWriter().println("Email: " + email + "<br>");
        response.getWriter().println("Password: " + password + "<br>");
        response.getWriter().println("Facebook: " + facebook + "<br>");
        response.getWriter().println("Gender: " + gender + "<br>");

        if (interests != null) {
            response.getWriter().print("Interests: ");
            for (String s : interests) {
                response.getWriter().print(s + "; ");
            }
            response.getWriter().println("<br>");
        }

        response.getWriter().println("Bio: " + bio + "<br>");
    }






}