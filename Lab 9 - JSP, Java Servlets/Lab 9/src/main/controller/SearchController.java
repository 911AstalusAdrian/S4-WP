package main.controller;


import main.domain.User;
import main.model.DatabaseManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@MultipartConfig
public class SearchController extends HttpServlet {

    public SearchController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;

        HashMap<Integer, User> users = new HashMap<>();

        System.out.println(request.getParameter("name"));

        DatabaseManager dbManager = new DatabaseManager();
        dbManager.searchByEmail(users, request.getParameter("email"));
        dbManager.searchByName(users, request.getParameter("name"));
        dbManager.searchByAge(users, request.getParameter("age"));
        dbManager.searchByHometown(users, request.getParameter("hometown"));

        JSONArray jsonCollection = new JSONArray();
        for (User user : users.values()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", Integer.toString(user.getId()));
            jsonObject.put("name", user.getName());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("age", user.getAge().toString());
            jsonObject.put("hometown", user.getHometown());
            jsonCollection.add(jsonObject);
        }
        session.setAttribute("search_result", jsonCollection);
        rd = request.getRequestDispatcher("/user/search.jsp");
        rd.forward(request, response);
    }
}
