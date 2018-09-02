<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 02.09.2018
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <h2>All products</h2>
    <c:forEach items="${products}" var="product">
        <tr>
            <td> Id: <c:out value="${product.id}" /><br></td>
            <td> Name: <c:out value="${product.name}" /><br></td>
            <td> Price: <c:out value="${product.price}" /><br></td><br>
        </tr>
    </c:forEach>
</body>
</html>
