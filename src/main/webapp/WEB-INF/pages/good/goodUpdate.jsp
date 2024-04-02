<%@ page import="com.stepashka.jsptask.entity.Manufacturer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.stepashka.jsptask.entity.Good" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product update page</title>
</head>
<body>
<h2>Edit fields:</h2>
<form action="good-update-servlet" method="post">
    <% Good good = (Good) request.getAttribute("good"); %>
    <input type="hidden" name="id" value="<%= good.getId() %>">
    <br/><label>Name: <input type="text" name="name" value="<%= good.getName() %>" required></label><br/>
    <br/><label>Model: <input type="text" name="model" value="<%= good.getModel() %>" required></label><br/>
    <br/><label>Guarantee: <input type="text" name="guarantee" value="<%= good.getGuarantee() %>"
                                  required></label><br/>
    <br/><label>Price: <input type="text" name="price" value="<%= good.getPrice() %>" required></label><br/>
    <br/><label>Description: <input type="text" name="description" value="<%= good.getDescription() %>"
                                    required></label><br/>
    <br/><label>Manufacturer: <select name="manufacturerId">
    <option value="<%= good.getManufacturer().getId() %>" selected><%= good.getManufacturer().getName() %>
    </option>
    <% for (Manufacturer manufacturer : (List<Manufacturer>) request.getAttribute("manufacturersList")) { %>
    <% if (!manufacturer.getId().equals(good.getManufacturer().getId())) { %>
    <option value="<%= manufacturer.getId()%>"><%= manufacturer.getName() %>
    </option>
    <% } %>
    <% } %>
</select></label><br/>
    <input type="submit" value="create">
</form>
<a href="good-read-all-servlet">Good menu</a><br/>
</body>
</html>
