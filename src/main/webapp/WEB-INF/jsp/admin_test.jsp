<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    <div class="row">

        <table class="table">
            <tr>
                <th>Назва</th>
                <td> ${test.name}</td>
            </tr>
            <tr>
                <th>Зміст</th>
                <td>${test.body}</td>
            </tr>
            <tr>
                <th>Умова</th>
                <td> ${test.condition}</td>
            </tr>
            <tr>
                <th>Тип</th>
                <td> ${test.testType}</td>
            </tr>

        </table>


        <form method="post" action="#">
            <input type="submit" value="Видалити">
        </form>
        <button><a href="/admin/test/${test.id}/edit">Змінити</a></button>
    </div>
</div>
</body>
</html>