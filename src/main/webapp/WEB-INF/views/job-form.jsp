<%@page import="com.jsp.job.tracking.entity.Company"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.job.tracking.entity.Job"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    Job job = (Job) request.getAttribute("job");

    List<Company> companies =
        (List<Company>) request.getAttribute("companies");

    boolean edit = job != null && job.getId() != null;
%>

<html>

<head>

    <title>Job</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/job-form.css">

</head>

<body class="job-form-page">

    <h2><%= edit ? "Edit Job" : "Add Job" %></h2>

    <form action="${pageContext.request.contextPath}/jobs/save" method="post">

        <% if (edit) { %>

        <input type="hidden"
               name="jobId"
               value="<%= job.getId() %>">

        <% } %>


        <label>Job Title</label>

        <input type="text"
               name="jobTitle"
               value="<%= edit ? job.getJobTitle() : "" %>"
               required>


        <label>Company</label>

        <select name="companyId" required>

            <%
            for (Company company : companies) {

                boolean selected = edit
                    && job.getCompany() != null
                    && job.getCompany().getId().equals(company.getId());
            %>

            <option value="<%= company.getId() %>"
                    <%= selected ? "selected" : "" %>>

                <%= company.getCompanyName() %>

            </option>

            <%
            }
            %>

        </select>


        <label>Location</label>

        <input type="text"
               name="location"
               value="<%= edit ? job.getLocation() : "" %>">


        <label>Experience</label>

        <input type="text"
               name="experience"
               value="<%= edit ? job.getExperience() : "" %>">


        <label>Salary</label>

        <input type="text"
               name="salary"
               value="<%= edit ? job.getSalary() : "" %>">


        <label>Job Type</label>

        <input type="text"
               name="jobType"
               value="<%= edit ? job.getJobType() : "" %>">


        <label>Description</label>

        <textarea name="description"
                  rows="6"
                  cols="50"><%= edit ? job.getDescription() : "" %></textarea>


        <label>Job URL</label>

        <input type="text"
               name="jobUrl"
               value="<%= edit ? job.getJobUrl() : "" %>">


        <label>Posted Date</label>

        <input type="date"
               name="postedDate"
               value="<%= edit && job.getPostedDate() != null ? job.getPostedDate() : "" %>">


        <label>Last Date</label>

        <input type="date"
               name="lastDate"
               value="<%= edit && job.getLastDate() != null ? job.getLastDate() : "" %>">


        <button type="submit">Save Job</button>

    </form>

</body>

</html>