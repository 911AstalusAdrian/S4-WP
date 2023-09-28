package main.controller;

import main.domain.User;
import main.model.DatabaseManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@MultipartConfig
public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;

        String email = request.getParameter("login_email");
        String password = request.getParameter("login_password");
        DatabaseManager dbManager = new DatabaseManager();
        User user = dbManager.login(email, password);
        if (user == null) {
            session.setAttribute("login_error_msg", "Invalid email or password.");
            rd = request.getRequestDispatcher("/login/error.jsp");
            rd.forward(request, response);
            return;
        }

        System.out.println(user.getId() + " " + user.getEmail());

        session.setAttribute("user", user);
        rd = request.getRequestDispatcher("/user/index.jsp");
        rd.forward(request, response);
    }

}