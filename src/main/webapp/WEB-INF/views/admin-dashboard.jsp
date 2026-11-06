<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Admin Dashboard</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-dashboard.css">
</head>
<body class="admin-dashboard">

<h2>Admin Dashboard</h2>

<a href="${pageContext.request.contextPath}/companies">Manage Companies</a><br><br>
<a href="${pageContext.request.contextPath}/jobs/admin/list">Manage Jobs</a><br><br>
<a href="${pageContext.request.contextPath}/jobs/add">Add Job</a><br><br>
<a href="${pageContext.request.contextPath}/logout">Logout</a>

</body>
</html>
