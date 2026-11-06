<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Login</title>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/login.css">
</head>

<body>
<h2>Job Application Tracking System</h2>

<%
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");

    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }

    if (success != null) {
%>
<p style="color:green;"><%= success %></p>
<%
    }
%>

<form action="${pageContext.request.contextPath}/login" method="post">
    Email:
    <input type="email" name="email" required><br><br>

    Password:
    <input type="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/register">Create Account</a>
</body>
</html>
