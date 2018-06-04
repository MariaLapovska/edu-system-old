<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    <h3>Результат</h3>
    <div class="row">
        <h4>Оцінка: ${mark}</h4>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Назва кадру</th>
                <th>Спроб</th>
                <th>Результат</th>
            </tr>
            </thead>
            <c:forEach var="attempt" items="${attempts}">
                <tr>
                    <td>${attempt.test.name}</td>
                    <td>${attempt.count}</td>
                    <td>${attempt.result}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>