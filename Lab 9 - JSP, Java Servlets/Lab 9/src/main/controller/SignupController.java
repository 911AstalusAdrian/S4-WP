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
public class SignupController extends HttpServlet{

    public SignupController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;

        if (request.getParameter("age") == null || request.getParameter("age").length() <= 0) {
            session.setAttribute("signup_error_msg", "Age can't be null.");
            rd = request.getRequestDispatcher("/signup/error.jsp");
            rd.forward(request, response);
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("password_confirm");
//        Part image = request.getPart("picture");
        Integer age = Integer.parseInt(request.getParameter("age"));
        String hometown = request.getParameter("hometown");

        if (name == null || name.length() == 0 || email == null || email.length() == 0 || password == null || password.length() == 0 || hometown == null || hometown.length() == 0) {
            session.setAttribute("signup_error_msg", "All fields are required!");
            rd = request.getRequestDispatcher("/signup/error.jsp");
            rd.forward(request, response);
            return;
        }
        if (age <= 0) {
            session.setAttribute("signup_error_msg", "Age must be a positive number.");
            rd = request.getRequestDispatcher("/signup/error.jsp");
            rd.forward(request, response);
            return;
        }
        if (!password.equals(confirmPassword)) {
            session.setAttribute("signup_error_msg", "Passwords don't match.");
            rd = request.getRequestDispatcher("/signup/error.jsp");
            rd.forward(request, response);
            return;
        }

        DatabaseManager dbManager = new DatabaseManager();
        if (dbManager.emailExists(email)) {
            session.setAttribute("signup_error_msg", "User with this email already exists.");
            rd = request.getRequestDispatcher("/signup/error.jsp");
            rd.forward(request, response);
            return;
        }

        User newUser = new User(name, email, password, age, hometown);
        User user = dbManager.signupUser(newUser);

        session.setAttribute("user", user);
        rd = request.getRequestDispatcher("/user/index.jsp");
        rd.forward(request, response);
    }
}
