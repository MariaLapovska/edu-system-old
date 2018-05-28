<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    <h3>Назва модулю: ${article.name}</h3>
    <br/>
    <h4>Теоретична частина</h4>
    ${article.body}
    <br/>
    <c:if test="${info != null}">
        <a href="/info/${info.id}">Перейти до першого кадру</a>
    </c:if>
</div>
</body>
</html>