<%--
  Created by IntelliJ IDEA.
  User: nobody
  Date: 24.12.2019
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <div>
            <%
            if (request.getAttribute("departmentName") != null) {
                out.println("<p>Department '" + request.getAttribute("departmentName") + "' was added!</p>");
            }
        %>
            <div>
                <h2>Add department</h2>
            </div>
<form method="post">
    <div class="form-group row">
        <label>Name:
            <input type="text" name="name" value="${department.getName()}"><br />
        </label>
    </div>
    <button class="btn btn-light" type="submit">Изменить данные</button>
</form>
            <div>
                <button onclick="location.href='/'">Back to main</button>
            </div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>