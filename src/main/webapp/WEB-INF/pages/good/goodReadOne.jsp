<%@ page import="com.stepashka.jsptask.entity.Good" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product information page</title>
</head>
<body>
<% Good good = (Good) request.getAttribute("good"); %>
<h2>Product: <%= good.getName() %>
</h2>
<p>Model: <%= good.getModel() %>
</p>
<p>Manufacturer: <%= good.getManufacturer().getName() %>
</p>
<p>Guarantee: <%= good.getGuarantee() %>
</p>
<p>Price: <%= good.getPrice() %>
</p>
<p>Description: <%= good.getDescription() %>
</p>
<a href="<%="good-delete-servlet?goodId=" + good.getId() %>">Delete product</a><br/>
<a href="<%="good-update-servlet?goodId=" + good.getId() %>">Update product</a><br/>
<a href="good-read-all-servlet">Good menu</a><br/>
</body>
</html>
