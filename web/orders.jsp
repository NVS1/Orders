<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 02.09.2018
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
    <h2>List of Orders</h2>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td> Id: <c:out value="${order.id}" /><br></td>
            <td> Product: <br>
                Name: <c:out value="${order.product.name}" /><br></td>
                Price: <c:out value="${order.product.price}" /><br></td>
            <td> User: <br>
                Name: <c:out value="${order.user.name}" /><br></td>
                Phone: <c:out value="${order.user.phone}" /><br></td>
                Email: <c:out value="${order.user.email}" /><br></td>
            <td> Quantity: <c:out value="${order.count}" /><br></td><br>
        </tr>
    </c:forEach>
</body>
</html>
