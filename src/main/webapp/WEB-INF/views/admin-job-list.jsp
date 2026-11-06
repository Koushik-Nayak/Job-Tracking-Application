<%@page import="com.jsp.job.tracking.entity.Job"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Manage Jobs</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/admin-job-list.css">

</head>

<body class="job-page">

    <%
    List<Job> jobs = (List<Job>) request.getAttribute("jobs");
    %>

    <h2>Manage Jobs</h2>

    <div class="job-top">

        <a class="add-job"
           href="${pageContext.request.contextPath}/jobs/add">
            Add New Job
        </a>

    </div>

    <table>

        <tr>
            <th>Job ID</th>
            <th>Job Title</th>
            <th>Company</th>
            <th>Location</th>
            <th>Experience</th>
            <th>Salary</th>
            <th>Actions</th>
            <th>Applications</th>
        </tr>

        <%
        if (jobs != null && !jobs.isEmpty()) {

            for (Job job : jobs) {
        %>

        <tr>

            <td><%= job.getId() %></td>

            <td><%= job.getJobTitle() %></td>

            <td><%= job.getCompany().getCompanyName() %></td>

            <td><%= job.getLocation() %></td>

            <td><%= job.getExperience() %></td>

            <td><%= job.getSalary() %></td>

            <td class="actions">

                <a href="${pageContext.request.contextPath}/jobs/view?id=<%= job.getId() %>">
                    View
                </a>

                <a href="${pageContext.request.contextPath}/jobs/edit?id=<%= job.getId() %>">
                    Edit
                </a>

                <a class="delete-link"
                   href="${pageContext.request.contextPath}/jobs/delete?id=<%= job.getId() %>"
                   onclick="return confirm('Are you sure you want to delete this job?');">
                    Delete
                </a>

            </td>

            <td>

                <a class="application-link"
                   href="${pageContext.request.contextPath}/applications/job?id=<%= job.getId() %>">
                    View Applications
                </a>

            </td>

        </tr>

        <%
            }

        } else {
        %>

        <tr>

            <td colspan="8" class="no-jobs">
                No jobs available.
            </td>

        </tr>

        <%
        }
        %>

    </table>

    <div class="bottom-links">

        <a href="${pageContext.request.contextPath}/dashboard/admin">
            Back to Admin Dashboard
        </a>

        <a class="logout-link"
           href="${pageContext.request.contextPath}/logout">
            Logout
        </a>

    </div>

</body>

</html>