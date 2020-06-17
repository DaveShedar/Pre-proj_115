<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<center>
    <h4>
        <a href="<%=request.getContextPath()%>/admin_newForm">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/admin">List All Users</a>

    </h4>
</center>
<div align="center">
    <table border="1" >
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUsers}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.password}" /></td>
                <td><c:out value="${user.role}" /></td>
                <td>
                    <a href="<%=request.getContextPath()%>/admin_edit?id=<c:out value="${user.id}" />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="<%=request.getContextPath()%>/admin/delete?id=<c:out value="${user.id}" />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>