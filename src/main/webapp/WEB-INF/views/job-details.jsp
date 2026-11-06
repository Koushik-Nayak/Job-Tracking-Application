<%@page import="com.jsp.job.tracking.entity.User"%>
<%@page import="com.jsp.job.tracking.entity.Job"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
Job job = (Job) request.getAttribute("job");

String error = (String) request.getAttribute("error");

String success = (String) request.getAttribute("success");

User loggedInUser = (User) session.getAttribute("loggedInUser");
%>

<html>

<head>

    <title>Job Details</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/job-details.css">

</head>

<body class="job-details-page">

    <% if (error != null) { %>

        <p class="error-message">
            <%= error %>
        </p>

    <% } %>

    <% if (success != null) { %>

        <p class="success-message">
            <%= success %>
        </p>

    <% } %>


    <div class="job-card">

        <h2><%= job.getJobTitle() %></h2>

        <p>
            <b>Company:</b>
            <%= job.getCompany().getCompanyName() %>
        </p>

        <p>
            <b>Location:</b>
            <%= job.getLocation() %>
        </p>

        <p>
            <b>Experience:</b>
            <%= job.getExperience() %>
        </p>

        <p>
            <b>Salary:</b>
            <%= job.getSalary() %>
        </p>

        <p>
            <b>Job Type:</b>
            <%= job.getJobType() %>
        </p>

        <p>
            <b>Posted Date:</b>
            <%= job.getPostedDate() %>
        </p>

        <p>
            <b>Last Date:</b>
            <%= job.getLastDate() %>
        </p>

        <div class="description">

            <b>Description:</b>

            <p>
                <%= job.getDescription() %>
            </p>

        </div>


        <% if (loggedInUser != null
                && loggedInUser.getRole().name().equals("JOB_SEEKER")) {
        %>

            <a class="apply-button"
               href="${pageContext.request.contextPath}/applications/apply?jobId=<%= job.getId() %>">
                APPLY NOW
            </a>

        <%
        }
        %>

    </div>


    <a class="back-link"
       href="${pageContext.request.contextPath}/jobs/list">
        Back to Jobs
    </a>

</body>

</html>