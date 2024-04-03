<%@ page import="com.stepashka.jsptask.entity.Storehouse" %>
<%@ page import="com.stepashka.jsptask.entity.Good" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Storehouse information page</title>
</head>
<body>
<% Storehouse storehouse = (Storehouse) request.getAttribute("storehouse"); %>
<h2>Storehouse: <%= storehouse.getAddress() %>
</h2>
<p>Square: <%= storehouse.getSquare() %>
</p>
<h2>Products in storehouse:</h2>
<% for (Map.Entry<Good, Integer> pair : storehouse.getGoods().entrySet()) { %>
<p><%= "Product: " + pair.getKey().getName() + " Quantity: " + pair.getValue()%>
</p>
<% } %>
<a href="<%="storehouse-add-product-servlet?storehouseId=" + storehouse.getId() %>">Add product</a><br/>
<a href="<%="storehouse-update-product-servlet?storehouseId=" + storehouse.getId() %>">Change product quantity</a><br/>
<a href="<%="storehouse-delete-servlet?storehouseId=" + storehouse.getId() %>">Delete storehouse</a><br/>
<a href="storehouse-read-all-servlet">Storehouse menu</a><br/>
</body>
</html>
