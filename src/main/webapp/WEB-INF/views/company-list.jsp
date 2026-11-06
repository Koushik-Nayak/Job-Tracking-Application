<%@page import="com.jsp.job.tracking.entity.Company"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
List<Company> companies = (List<Company>) request.getAttribute("companies");
%>

<html>

<head>
<title>Companies</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/company-list.css">
</head>

<body class="companies-page">

	<h2>Companies</h2>

	<%
	String error = request.getParameter("error");

	if ("jobs".equals(error)) {
	%>

	<div class="warning-message" id="warningMessage">
    <span>⚠️ Cannot delete this company because jobs are associated with it. Please remove or deactivate the jobs first.</span>
    <button onclick="document.getElementById('warningMessage').style.display='none'">
        ×
    </button>
</div>

	<%
}
%>

	<div class="company-links">

		<a href="${pageContext.request.contextPath}/dashboard/admin">
			Admin Dashboard </a> <a
			href="${pageContext.request.contextPath}/companies/add"> Add
			Company </a>

	</div>

	<table>

		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Location</th>
			<th>Industry</th>
			<th>Website</th>
			<th>Actions</th>
		</tr>

		<%
		for (Company company : companies) {
		%>

		<tr>

			<td><%=company.getId()%></td>

			<td><%=company.getCompanyName()%></td>

			<td><%=company.getLocation()%></td>

			<td><%=company.getIndustry()%></td>

			<td><%=company.getWebsite()%></td>

			<td><a
				href="${pageContext.request.contextPath}/companies/edit?id=<%= company.getId() %>">
					Edit </a> <a
				href="${pageContext.request.contextPath}/companies/delete?id=<%= company.getId() %>">
					Delete </a></td>

		</tr>

		<%
		}
		%>

	</table>

</body>

</html>