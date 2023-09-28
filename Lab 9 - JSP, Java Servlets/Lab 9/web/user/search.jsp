<%@ page import="main.domain.User" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/index.html");
            return;
        }
        User user = (User) session.getAttribute("user");
    %>
    <title> Hello, <% out.println(user.getName()); %>! </title>
</head>
<body>
    <a href="/user/update.jsp"> Update profile </a>
    <br>
    <form action="SearchController" method="get" enctype="multipart/form-data">
        Enter email: <input type="email" name="email"> <BR>
        Enter name: <input type="text" name="name"> <BR>
        Enter age: <input type="number" name="age"> <BR>
        Enter hometown: <input type="text" name="hometown"> <BR>
        <input type="submit" value="Search"/>
    </form>

    <%
        if (session.getAttribute("search_result") != null) {
            JSONArray jsonCollection = (JSONArray) session.getAttribute("search_result");
            out.println("<table>");
            out.println("<thead>");
            out.println("<th> id </th>");
            out.println("<th> name </th>");
            out.println("<th> email </th>");
            out.println("<th> age </th>");
            out.println("<th> image </th>");
            out.println("<th> hometown </th>");
            out.println("</thead>");
            out.println("<tbody>");
            for (Object object : jsonCollection) {
                JSONObject jsonObject = (JSONObject) object;
                out.println("<tr>");
                out.println("<td>" + jsonObject.get("id") + "</td>");
                out.println("<td>" + jsonObject.get("name") + "</td>");
                out.println("<td>" + jsonObject.get("email") + "</td>");
                out.println("<td>" + jsonObject.get("age") + "</td>");
                out.println("<td>" + "<img width='100px' src='GetImageController?id=" + jsonObject.get("id") + "'/>" + "</td>");
                out.println("<td>" + jsonObject.get("hometown") + "</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
        }
        session.setAttribute("search_result", null);
    %>
</body>
</html>
