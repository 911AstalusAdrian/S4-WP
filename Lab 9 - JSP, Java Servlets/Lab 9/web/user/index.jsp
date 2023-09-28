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
<a href="/user/update.jsp"> Update profile </a>

</body>
</html>
