<%@page import="com.jsp.job.tracking.entity.Job"%>
<%@page import="com.jsp.job.tracking.entity.JobApplication"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
List<JobApplication> applications =
    (List<JobApplication>) request.getAttribute("applications");

Job job = (Job) request.getAttribute("job");
%>

<html>

<head>

    <title>Applications</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/application-list.css">

</head>

<body class="application-page">

    <h2>
        Applications for
        <%= job.getJobTitle() %>
    </h2>

    <table>

        <tr>
            <th>Candidate</th>
            <th>Email</th>
            <th>Applied Date</th>
            <th>Status</th>
            <th>Interview Date</th>
            <th>Action</th>
        </tr>

        <%
        for (JobApplication jobApplication : applications) {
        %>

        <tr>

            <td>
                <%= jobApplication.getUser().getName() %>
            </td>

            <td>
                <%= jobApplication.getUser().getEmail() %>
            </td>

            <td>
                <%= jobApplication.getAppliedDate() %>
            </td>

            <td>
                <%= jobApplication.getStatus() %>
            </td>

            <td>
                <%= jobApplication.getInterviewDate() == null
                    ? "-"
                    : jobApplication.getInterviewDate() %>
            </td>

            <td>
                <a class="update-link"
                   href="${pageContext.request.contextPath}/applications/update?id=<%= jobApplication.getId() %>">
                    Update
                </a>
            </td>

        </tr>

        <%
        }
        %>

    </table>

</body>

</html>