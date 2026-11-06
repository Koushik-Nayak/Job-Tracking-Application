<%@page import="com.jsp.job.tracking.entity.User"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<%
User user = (User) session.getAttribute("loggedInUser");
%>

<html>

<head>

<title>Dashboard</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/dashboard.css">

</head>

<body class="dashboard-page">

	<h2>
		Welcome,
		<%=user.getName()%></h2>

	<h3>Application Statistics</h3>

	<table>

		<tr>

			<th>Total</th>

			<th>Applied</th>

			<th>Interview</th>

			<th>Selected</th>

			<th>Rejected</th>

		</tr>

		<tr>

			<td><%=request.getAttribute("total")%></td>

			<td><%=request.getAttribute("applied")%></td>

			<td><%=request.getAttribute("interview")%></td>

			<td><%=request.getAttribute("selected")%></td>

			<td><%=request.getAttribute("rejected")%></td>

		</tr>

	</table>

	<div class="dashboard-links">

		<a href="${pageContext.request.contextPath}/jobs/list"> Browse
			Jobs </a> <a href="${pageContext.request.contextPath}/applications/my">
			My Applications </a> <a class="logout-link"
			href="${pageContext.request.contextPath}/logout"> Logout </a>

	</div>

</body>

</html>