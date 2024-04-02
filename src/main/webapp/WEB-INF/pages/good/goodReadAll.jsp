<%@ page import="com.stepashka.jsptask.entity.Good" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product list page</title>
</head>
<body>
<h2>Product list:</h2>
<ul>
    <% for (Good good : (List<Good>) request.getAttribute("goodsList")) { %>
    <li>
        <a href= <%="good-read-one-servlet?goodId=" + good.getId() %>><%= "Product: " + good.getName() %>
        </a>
    </li>
    <% } %>
</ul>
<a href="good-insert-servlet">Add product</a><br/>
<a href="index.jsp">Main menu</a><br/>
</body>
</html>
