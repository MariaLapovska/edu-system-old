<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>

Назва категорії: ${category.name}
<h3>Ствроені .... шото</h3>
<c:forEach var="article" items="${articles}">
    <a href="/admin/article/${article.id}">${article.name}</a>
    ${article.body}
    <br/>
</c:forEach>
<form method="post" action="#">
    <input type="text" name="name">
    <input type="text" name="body">
    <input type="submit" value="Создать">
</form>
</body>
</html>