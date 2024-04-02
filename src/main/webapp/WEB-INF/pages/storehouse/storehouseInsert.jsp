<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Storehouse creating page</title>
</head>
<body>
<h2>Fill information:</h2>
<form action="storehouse-insert-servlet" method="post">
    <br/><label>Address: <input type="text" name="address" required></label><br/>
    <br/><label>Square: <input type="text" name="square" required></label><br/>
    <input type="submit" value="create">
</form>
<a href="storehouse-read-all-servlet">Storehouse menu</a><br/>
</body>
</html>