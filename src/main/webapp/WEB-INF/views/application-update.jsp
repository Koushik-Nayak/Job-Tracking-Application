<%@page import="com.jsp.job.tracking.entity.ApplicationStatus"%>
<%@page import="com.jsp.job.tracking.entity.JobApplication"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    JobApplication jobApplication =
        (JobApplication) request.getAttribute("application");

    ApplicationStatus[] statuses =
        (ApplicationStatus[]) request.getAttribute("statuses");
%>

<html>

<head>

    <title>Update Application</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/application-update.css">

</head>

<body class="application-update-page">

    <h2>Update Application</h2>

    <div class="application-info">

        <p>
            <strong>Candidate:</strong>
            <%= jobApplication.getUser().getName() %>
        </p>

        <p>
            <strong>Job:</strong>
            <%= jobApplication.getJob().getJobTitle() %>
        </p>

    </div>

    <form action="${pageContext.request.contextPath}/applications/update?id=<%= jobApplication.getId() %>"
          method="post">

        <label>Status</label>

        <select name="status">

            <%
            for (ApplicationStatus status : statuses) {
            %>

            <option value="<%= status %>"
                <%= jobApplication.getStatus() == status ? "selected" : "" %>>

                <%= status %>

            </option>

            <%
            }
            %>

        </select>


        <label>Interview Date</label>

        <input type="date"
               name="interviewDate"
               value="<%= jobApplication.getInterviewDate() == null ? "" : jobApplication.getInterviewDate() %>">


        <label>Interview Time</label>

        <input type="text"
               name="interviewTime"
               value="<%= jobApplication.getInterviewTime() == null ? "" : jobApplication.getInterviewTime() %>">


        <label>Notes</label>

        <textarea name="notes"
                  rows="5"
                  cols="50"><%= jobApplication.getNotes() == null ? "" : jobApplication.getNotes() %></textarea>


        <button type="submit">
            Update Status
        </button>

    </form>

</body>

</html>