<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>
<center>
    <h3>
        <a href="/admin_newForm">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/admin">List All Users</a>

    </h3>
</center>
<div align="center">
    <c:if test="${user != null}">
    <form action="/admin_edit" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="/admin_newForm" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Edit User
                        </c:if>
                        <c:if test="${user == null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>
                <tr>
                    <th>User Name: </th>
                    <td>
                        <input type="text" name="name" size="45" value="<c:out value="${user.name}"/>" />
                    </td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td>
                        <input type="text" name="password" size="45" value="<c:out value='${user.password}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Role: </th>
                    <td>
                        <input type="text" name="role" size="15" value="<c:out value='${user.role}' />"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>