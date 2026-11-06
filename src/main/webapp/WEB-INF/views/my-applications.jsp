<%@page import="com.jsp.job.tracking.entity.JobApplication"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
List<JobApplication> applications =
        (List<JobApplication>) request.getAttribute("applications");
%>

<html>

<head>

<title>My Applications</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/my-applications.css">

</head>

<body class="my-applications-page">

<h2>My Applications</h2>

<form class="filter-form"
      action="${pageContext.request.contextPath}/applications/filter"
      method="get">

    <select name="status">

        <option value="ALL">All</option>

        <option value="APPLIED">Applied</option>

        <option value="INTERVIEW">Interview</option>

        <option value="SELECTED">Selected</option>

        <option value="REJECTED">Rejected</option>

    </select>

    <button type="submit">Filter</button>

</form>

<table>

    <tr>
        <th>Job</th>
        <th>Company</th>
        <th>Applied Date</th>
        <th>Status</th>
        <th>Interview Date</th>
        <th>Interview Time</th>
        <th>Notes</th>
        <th>Job Status</th>
    </tr>

    <%
    for (JobApplication jobApplication : applications) {
    %>

    <tr>

        <td>
            <%= jobApplication.getJob().getJobTitle() %>
        </td>

        <td>
            <%= jobApplication.getJob().getCompany().getCompanyName() %>
        </td>

        <td>
            <%= jobApplication.getAppliedDate() %>
        </td>

        <td class="application-status">
            <%= jobApplication.getStatus() %>
        </td>

        <td>
            <%= jobApplication.getInterviewDate() == null
                ? "-"
                : jobApplication.getInterviewDate() %>
        </td>

        <td>
            <%= jobApplication.getInterviewTime() == null
                ? "-"
                : jobApplication.getInterviewTime() %>
        </td>

        <td>
            <%= jobApplication.getNotes() == null
                ? "-"
                : jobApplication.getNotes() %>
        </td>

        <td>

            <%
            if (jobApplication.getJob().isActive()) {
            %>

                <span class="job-available">Available</span>

            <%
            } else {
            %>

                <span class="job-closed">Job Closed</span>

            <%
            }
            %>

        </td>

    </tr>

    <%
    }
    %>

</table>

<div class="bottom-links">

    <a href="${pageContext.request.contextPath}/dashboard">
        Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/jobs/list">
        Browse Jobs
    </a>

    <a class="logout-link"
       href="${pageContext.request.contextPath}/logout">
        Logout
    </a>

</div>

</body>

</html>