<%@ page import="main.domain.User" %>
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
    <a href="/user/search.jsp"> Search database </a>
    <br>
    <form action="UpdateController" method="post" enctype="multipart/form-data">
        Enter email: <input type="email" name="email"> <BR>
        Enter name: <input type="text" name="name"> <BR>
        Enter password: <input type="password" name="password"> <BR>
        Confirm password: <input type="password" name="password_confirm"> <BR>
        Enter age: <input type="number" name="age"> <BR>
        Enter picture: <input type="file" name="picture"> <BR>
        Enter hometown: <input type="text" name="hometown"> <BR>
        <input type="submit" value="Update profile!"/>
    </form>

    <%
        if (session.getAttribute("update_msg") != null) {
            out.println(session.getAttribute("update_msg"));
        }
        session.setAttribute("update_msg", "");
    %>
</body>
</html>
