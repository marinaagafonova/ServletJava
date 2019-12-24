<%@ page import="java.util.List" %>
<%@ page import="app.domain.Department" %><%--
  Created by IntelliJ IDEA.
  User: nobody
  Date: 23.12.2019
  Time: 3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>
<div>
    <div>
        <h2>Departments</h2>
    </div>
    <%
        List<Department> departments = (List<Department>) request.getAttribute("departments");

        if (departments != null) {
            out.println("<ui>");
            for (Department d : departments) {
                out.println("<li>" + d.getName() + "</li>" + "<a href=\"/edit?id="+ d.getId() + "\"/>Edit</a>" +
                        "<a href=\"/delete?id="+ d.getId() + "\"/>Delete</a>");


            }
            out.println("</ui>");
        } else out.println("<p>There are no departments yet</p>");
    %>
</div>
<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
