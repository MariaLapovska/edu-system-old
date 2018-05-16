<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<h3>Головна сторінка</h3>
<c:forEach var="category" items="${categories}">
    <a href="/admin/category/${category.id}">${category.name}</a>
    <br/>
</c:forEach>
<form method="post" action="#">
    <input type="text" name="name">
    <input type="submit" value="Создать">
</form>
</body>
</html>