<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>

Назва чего-то: ${article.name}
<h3>Створені тесты</h3>
<c:forEach var="test" items="${tests}">
    <a href="/admin/test/${test.id}">${test.name}</a>
    ${test.body}
    ${test.testType}
    <br/>
</c:forEach>
<form method="post" action="#">
    Тип:
    <input type="radio" name="type" id="type-open" value="OPEN">
    <label for="type-open">Відкрита відповідь</label>
    <input type="text" name="name">
    <input type="text" name="body">
    <input type="text" name="condition">
    <input type="submit" value="Создать">
</form>
</body>
</html>