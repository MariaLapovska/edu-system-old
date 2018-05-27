<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    Назва чего-то: ${article.name}
    <br/>
    ${article.body}

    <c:if test="${test != null}">
        <a href="/test/${test.id}">Перейти до тестування</a>
    </c:if>
</div>
</body>
</html>