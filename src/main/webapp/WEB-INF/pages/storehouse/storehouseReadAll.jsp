<%@ page import="com.stepashka.jsptask.entity.Storehouse" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Storehouse list page</title>
</head>
<body>
<h2>Storehouse list:</h2>
<ul>
    <% for (Storehouse storehouse : (List<Storehouse>) request.getAttribute("storehouseList")) { %>
    <li>
        <a href= <%="storehouse-read-one-servlet?storehouseId=" + storehouse.getId() %>><%= "storehouse: " + storehouse.getAddress() %>
        </a>
    </li>
    <% } %>
</ul>
<a href="storehouse-insert-servlet">Add storehouse</a><br/>
<a href="index.jsp">Main menu</a><br/>
</body>
</html>
