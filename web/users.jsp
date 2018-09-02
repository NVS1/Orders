<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 02.09.2018
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <h2>List of Users</h2>
    <c:forEach items="${users}" var="user">
        <tr>
            <td> Id: <c:out value="${user.id}" /><br></td>
            <td> Name: <c:out value="${user.name}" /><br></td>
            <td> Email: <c:out value="${user.email}" /><br></td>
            <td> Phone: <c:out value="${user.phone}" /><br></td><br>
        </tr>
    </c:forEach>
</body>
</html>
