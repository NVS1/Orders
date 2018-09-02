<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 02.09.2018
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page</title>
</head>
<body>
    <h2>Add User</h2>
    <form action="/users" method="post">
        Name: <input type="text" name="name"><br>
        Email <input type="text" name="email"><br>
        Phone <input type="text" name="phone"><br>
        <input type="submit"><br>
    </form>
    <a href="http://localhost:8080/users">View all users</a><br>
    <h2>Add Product</h2>
    <form action="/products" method="post">
        Name: <input type="text" name="name"><br>
        Price: <input type="number" name="price"><br>
        <input type="submit"><br>
    </form>
    <a href="http://localhost:8080/products">View all products</a><br>
    <h2>Add Order</h2>
    <form action="orders" method="post">
        Phone number: <input type="text" name="phone"><br>
        Product name: <input type="text" name="name"><br>
        Quantity: <input type="number" name="count"><br>
        <input type="submit"><br>
    </form>
    <a href="http://localhost:8080/orders">View all orders</a><br>
</body>
</html>
