package org.northernjay.hospital_management_system.controller;

import org.northernjay.hospital_management_system.utils.AuthenticationUtil;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (AuthenticationUtil.isValid(username, password)) {
            System.out.println("Authentication succeeded");
        } else {
            System.out.println("Authentication Failed");
        }
    }

    public void destroy() {
    }
}