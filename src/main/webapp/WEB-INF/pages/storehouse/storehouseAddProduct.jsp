<%@ page import="com.stepashka.jsptask.entity.Good" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Storehouse adding product page</title>
</head>
<body>
<h2>Fill information:</h2>
<% Integer storehouseId = (Integer) request.getAttribute("storehouseId"); %>
<% List<Good> goods = (List<Good>) request.getAttribute("goodsList"); %>
<form action="storehouse-add-product-servlet" method="post">
    <input type="hidden" name="storehouseId" value="<%= storehouseId %>">
    <label>Product: <select name="goodId">
        <% for (Good good : goods) { %>
        <option value="<%= good.getId() %>"><%= good.getName() %>
        </option>
        <% } %>
    </select></label><br/>
    <br/><label>Quantity: <input type="text" name="quantity" required></label><br/>
    <input type="submit" value="Add">
</form>
<a href="<%="storehouse-read-one-servlet?storehouseId=" + storehouseId %>">Back to storehouse</a><br/>
</body>
</html>