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
public class UpdateController extends HttpServlet {

    public UpdateController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;

        if (session.getAttribute("user") == null) {
            session.setAttribute("user_error_msg", "Something went wrong, please try again.");
            rd = request.getRequestDispatcher("/user/error.jsp");
            rd.forward(request, response);
            return;
        }
        DatabaseManager dbManager = new DatabaseManager();
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();

        String name = request.getParameter("name");
        if (dbManager.updateName(id, name)) {
            user.setName(name);
        } else {
            session.setAttribute("update_msg", session.getAttribute("update_msg") + " <br> Name could not be updated");
        }

        String email = request.getParameter("email");
        if (!dbManager.emailExists(email) && dbManager.updateEmail(id, email)) {
            user.setEmail(email);
        } else {
            session.setAttribute("update_msg", session.getAttribute("update_msg") + " <br> Email could not be updated");
        }

        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("password_confirm");
        if (dbManager.updatePassword(id, password, confirmPassword)) {
            user.setPassword(password);
        } else {
            session.setAttribute("update_msg", session.getAttribute("update_msg") + " <br> Password could not be updated");
        }

        if (dbManager.updateAge(id, request.getParameter("age"))) {
            user.setAge(Integer.parseInt(request.getParameter("age")));
        } else {
            session.setAttribute("update_msg", session.getAttribute("update_msg") + " <br> Age could not be updated");
        }

        String hometown = request.getParameter("hometown");
        if (dbManager.updateHometown(id, hometown)) {
            user.setHometown(hometown);
        } else {
            session.setAttribute("update_msg", session.getAttribute("update_msg") + " <br> Hometown could not be updated");
        }

        session.setAttribute("update_msg", session.getAttribute("update_msg") + " <br> update complete");
        rd = request.getRequestDispatcher("/user/update.jsp");
        rd.forward(request, response);
    }

}
