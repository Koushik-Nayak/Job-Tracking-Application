<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Register</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>

<h2>Job Seeker Registration</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }
%>

<form action="${pageContext.request.contextPath}/register" method="post">

    Name:
    <input type="text" name="name" required><br><br>

    Email:
    <input type="email" name="email" required><br><br>

    Password:
    <input type="password" name="password" required><br><br>

    <button type="submit">Register</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/login">Back to Login</a>

</body>
</html>
