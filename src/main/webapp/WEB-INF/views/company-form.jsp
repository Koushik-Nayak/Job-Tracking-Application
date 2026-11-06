<%@page import="com.jsp.job.tracking.entity.Company"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    Company company = (Company) request.getAttribute("company");

    boolean edit = company != null;
%>

<html>

<head>

    <title>Company</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/company-form.css">

</head>

<body class="company-form-page">

    <h2><%= edit ? "Edit Company" : "Add Company" %></h2>

    <form action="${pageContext.request.contextPath}/companies/save" method="post">

        <% if (edit) { %>

        <input type="hidden"
               name="companyId"
               value="<%= company.getId() %>">

        <% } %>


        <label>Company Name</label>

        <input type="text"
               name="companyName"
               value="<%= edit ? company.getCompanyName() : "" %>"
               required>


        <label>Location</label>

        <input type="text"
               name="location"
               value="<%= edit ? company.getLocation() : "" %>">


        <label>Website</label>

        <input type="text"
               name="website"
               value="<%= edit ? company.getWebsite() : "" %>">


        <label>Industry</label>

        <input type="text"
               name="industry"
               value="<%= edit ? company.getIndustry() : "" %>">


        <button type="submit">Save</button>

    </form>

    <a class="back-link"
       href="${pageContext.request.contextPath}/companies">
        Back
    </a>

</body>

</html>