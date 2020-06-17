<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<center>
</center>
<div align="center">
    <table border="1">
        <caption><h2>User information</h2></caption>
        <tr>
            <th>Name</th>
            <th>Password</th>
            <th>Role</th>
        </tr>
            <tr>
                <td><%= request.getSession().getAttribute("name") %></td>
                <td><%= request.getSession().getAttribute("password") %></td>
                <td><%= request.getSession().getAttribute("role") %></td>
            </tr>
    </table>
</div>
</body>
</html>
