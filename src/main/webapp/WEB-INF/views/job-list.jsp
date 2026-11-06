<%@page import="com.jsp.job.tracking.entity.Job"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
List<Job> jobs = (List<Job>) request.getAttribute("jobs");
String keyword = (String) request.getAttribute("keyword");
%>

<html>

<head>
<title>Jobs</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/job-list.css">
</head>

<body class="job-list-page">

	<h2>Available Jobs</h2>

	<form class="search-form"
		action="${pageContext.request.contextPath}/jobs/search" method="get">

		<input type="text" name="keyword"
			placeholder="Search job, company or location"
			value="<%=keyword == null ? "" : keyword%>">

		<button type="submit">Search</button>

	</form>

	<%
	if (jobs != null && !jobs.isEmpty()) {

		for (Job job : jobs) {
	%>

	<div class="job-card">

		<h3><%=job.getJobTitle()%></h3>

		<p>
			<b>Company:</b>
			<%=job.getCompany().getCompanyName()%>
		</p>

		<p>
			<b>Location:</b>
			<%=job.getLocation()%>
		</p>

		<p>
			<b>Experience:</b>
			<%=job.getExperience()%>
		</p>

		<p>
			<b>Salary:</b>
			<%=job.getSalary()%>
		</p>

		<a class="view-link"
			href="${pageContext.request.contextPath}/jobs/view?id=<%= job.getId() %>">
			View Details </a>

	</div>

	<%
	}

	} else {
	%>

	<p class="no-jobs">No jobs found.</p>

	<%
	}
	%>

	<div class="bottom-links">

		<a href="${pageContext.request.contextPath}/dashboard"> Dashboard
		</a> <a href="${pageContext.request.contextPath}/applications/my"> My
			Applications </a> <a class="logout-link"
			href="${pageContext.request.contextPath}/logout"> Logout </a>

	</div>

</body>

</html>