<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lethethuy
  Date: 01/08/2023
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        h1,table{
            width: 60%;
            text-align: center;
            margin: 0 auto 50px;
        }
    </style>
</head>
<body>
<h1>DANH SACH SINH VIEN</h1>
<a href="./view/newStuden.jsp">Add</a>
<table border="10" cellpadding="10" cellspacing="20" style="text-align: center">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="s">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.age}</td>
            <td><a href="/home-servlet?action=EDIT&id=${s.id}">Edit</a></td>
            <td><a onclick="return confirm('Do you want delete this student?')" href="/home-servlet?action=DELETE&id=${s.id}">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
