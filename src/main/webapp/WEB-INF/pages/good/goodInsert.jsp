<%@ page import="com.stepashka.jsptask.entity.Manufacturer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Product crating page</title>
</head>
<body>
<h2>Fill information:</h2>
<form action="good-insert-servlet" method="post">
    <br/><label>Name: <input type="text" name="name" required></label><br/>
    <br/><label>Model: <input type="text" name="model" required></label><br/>
    <br/><label>Guarantee: <input type="text" name="guarantee" required></label><br/>
    <br/><label>Price: <input type="text" name="price" required></label><br/>
    <br/><label>Description: <input type="text" name="description" required></label><br/>
    <br/><label>Manufacturer: <select name="manufacturerId">
    <% for (Manufacturer manufacturer : (List<Manufacturer>) request.getAttribute("manufacturersList")) { %>
    <option value="<%= manufacturer.getId()%>"><%= manufacturer.getName() %>
    </option>
    <% } %>
</select></label><br/><br/>
    <input type="submit" value="create">
</form>
<a href="good-read-all-servlet">Good menu</a><br/>
</body>
</html>
