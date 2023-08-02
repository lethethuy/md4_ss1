<%--
  Created by IntelliJ IDEA.
  User: lethethuy
  Date: 02/08/2023
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<h1>Edit Student Information</h1>
<form action="/home-servlet" method="post">
    <input type="hidden" name="action" value="UPDATE">
    <input type="hidden" name="id" value="${studentToEdit.id}">
    <label for="name">Name:</label>
    <input type="text" name="name" value="${studentToEdit.name}" required><br>
    <label for="age">Age:</label>
    <input type="number" name="age" value="${studentToEdit.age}" required><br>
    <input type="submit" value="Save">
</form>

</body>
</html>
