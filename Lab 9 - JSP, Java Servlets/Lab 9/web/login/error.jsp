<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<% String message = (String) session.getAttribute("login_error_msg"); %>
Signup failed! <br>
<% out.println(message); %>
</body>
</html>